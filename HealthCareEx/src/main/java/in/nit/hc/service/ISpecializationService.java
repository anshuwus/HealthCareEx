package in.nit.hc.service;

import java.util.List;

import in.nit.hc.entity.Specialization;

public interface ISpecializationService {
	public Long saveSpecialization(Specialization spec);
	public List<Specialization> getAllSpecialization();
	public void removeSpecialization(Long id);
	public Specialization getOneSpecialization(Long id);
	public void updateSpecialization(Specialization spec);
	
	public boolean isSpecCodeExit(String specCode);
	public boolean isSpecNameExit(String specName);
}
