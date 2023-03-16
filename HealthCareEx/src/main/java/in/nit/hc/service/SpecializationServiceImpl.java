package in.nit.hc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.hc.entity.Specialization;
import in.nit.hc.repository.ISpecializationRepository;

@Service
public class SpecializationServiceImpl implements ISpecializationService {

	@Autowired
	private ISpecializationRepository repo;
	
	@Override
	public Long saveSpecialization(Specialization spec) {
		return repo.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSpecialization() {
		return repo.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Specialization getOneSpecialization(Long id) {
		return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		repo.save(spec);
	}

}
