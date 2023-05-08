package in.nit.hc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.hc.constants.UserRoles;
import in.nit.hc.entity.Doctor;
import in.nit.hc.entity.User;
import in.nit.hc.exception.DoctorNotFoundException;
import in.nit.hc.repository.IDoctorRepository;
import in.nit.hc.util.MyCollection;
import in.nit.hc.util.MyMailUtil;
import in.nit.hc.util.UserUtil;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private IDoctorRepository repo;
	@Autowired
	private IUserService userService;
	@Autowired
	private UserUtil util;
	@Autowired
	private MyMailUtil mailUtil;
	
	@Override
	public Long saveDoctor(Doctor doc) {
		Long id = repo.save(doc).getId();
		if(id!=null) {
			User user=new User();
			String pwd = util.genPwd();
			user.setDisplayName(doc.getFirstName()+" "+doc.getLastName());
			user.setUsername(doc.getEmail());
			user.setPassword(pwd);
			user.setRole(UserRoles.DOCTOR.name());
			Long genId = userService.saveUser(user);
			//sending Email to doctor
			if(genId!=null) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						String text = "Your name is "+doc.getEmail()+", password is "+pwd;
						mailUtil.send(doc.getEmail(), "DOCTOR ADDED", text);
					}
				}).start();
			}
		}
		return id;
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return repo.findAll();
	}

	@Override
	public void removeDoctor(Long id) {
		repo.delete(getOneDoctor(id));
	}
	
	@Override
	public Doctor getOneDoctor(Long id) {
		return repo.findById(id).orElseThrow(() -> new DoctorNotFoundException(id+", not exist"));
	}

	@Override
	public void updateDoctor(Doctor doc) {
		if(repo.existsById(doc.getId())) {
			repo.save(doc);
		}else
			throw new DoctorNotFoundException(doc.getId()+", not exist");
	}

	@Override
	public Map<Long,String> getDocIdAndName() {
		List<Object[]> list=repo.getDocIdAndName();
		Map<Long,String> map=MyCollection.convertListToMap2(list);
		return map;
	}

	@Override
	public List<Doctor> findDoctorBySpecId(Long id) {
		// TODO Auto-generated method stub
		return repo.findDoctorBySpecId(id);
	}

}
