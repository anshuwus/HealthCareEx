package in.nit.hc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.hc.constants.UserRoles;
import in.nit.hc.entity.Patient;
import in.nit.hc.entity.User;
import in.nit.hc.exception.PatientNotFoundException;
import in.nit.hc.repository.IPatientRepository;
import in.nit.hc.util.UserUtil;

@Service
public class PatientServiceImpl implements IPatientService {
	
	@Autowired
	private IPatientRepository repo;
	@Autowired
	private IUserService userService;
	@Autowired
	private UserUtil util;
	
	@Override
	public Long savePatient(Patient pat) {
		Long id = repo.save(pat).getId();
		if(id!=null) {
			User user=new User();
			user.setDisplayName(pat.getFirstName()+" "+pat.getLastName());
			user.setUsername(pat.getEmail());
			user.setPassword(util.genPwd());
			user.setRole(UserRoles.PATIENT.name());
			userService.saveUser(user);
			//TODO : Email part is pending
		}
		return id;
	}

	@Override
	public List<Patient> getAllPatient() {
		return repo.findAll();
	}

	@Override
	public void removePatient(Long id) {
		repo.delete(getOnePatient(id));
	}

	@Override
	public Patient getOnePatient(Long id) {
		return repo.findById(id).orElseThrow(() -> new PatientNotFoundException(id+", not exist"));
	}

	@Override
	public void updatePatient(Patient pat) {
		if(repo.existsById(pat.getId())) {
			repo.save(pat);
		}
		else
			throw new PatientNotFoundException(pat.getId()+", not exist");
	}

}
