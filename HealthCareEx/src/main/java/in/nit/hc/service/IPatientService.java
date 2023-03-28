package in.nit.hc.service;

import java.util.List;

import in.nit.hc.entity.Patient;

public interface IPatientService {
	
	public Long savePatient(Patient pat);
	public List<Patient> getAllPatient();
	public void removePatient(Long id);
	public Patient getOnePatient(Long id);
	public void updatePatient(Patient pat);
}

