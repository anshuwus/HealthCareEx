package in.nit.hc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.hc.entity.Patient;
import in.nit.hc.exception.PatientNotFoundException;
import in.nit.hc.repository.IPatientRepository;

@Service
public class PatientServiceImpl implements IPatientService {
	
	@Autowired
	private IPatientRepository repo;
	
	@Override
	public Long savePatient(Patient pat) {
		return repo.save(pat).getId();
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
