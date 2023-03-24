package in.nit.hc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.hc.entity.Doctor;
import in.nit.hc.exception.DoctorNotFoundException;
import in.nit.hc.repository.IDoctorRepository;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private IDoctorRepository repo;
	
	@Override
	public Long saveDoctor(Doctor doc) {
		return repo.save(doc).getId();
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

}
