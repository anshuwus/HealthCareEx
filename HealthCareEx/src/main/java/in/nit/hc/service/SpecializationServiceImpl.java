package in.nit.hc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.hc.entity.Specialization;
import in.nit.hc.exception.SpecializationNotFoundException;
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
		//repo.deleteById(id);
		repo.delete(getOneSpecialization(id));
	}

	@Override
	public Specialization getOneSpecialization(Long id) {
		/*	Optional<Specialization> optional=repo.findById(id);
			if(optional.isPresent()) {
				return optional.get();
			}
			else {
				throw new SpecializationNotFoundException(id+", id not found");
			}*/
		return repo.findById(id).orElseThrow(() -> new SpecializationNotFoundException(id+", id not found"));
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		repo.save(spec);
	}

	@Override
	public boolean isSpecCodeExit(String specCode) {
		/*Integer count=repo.getSpecCodeCount(speccCode);
		boolean exit = count>0?true:false;
		return exit; */
		return repo.getSpecCodeCount(specCode)>0;
	}

	@Override
	public boolean isSpecNameExit(String specName) {
		return repo.getSpecNameCount(specName)>0;
	}

}
