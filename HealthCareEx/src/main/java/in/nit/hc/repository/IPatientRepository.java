package in.nit.hc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.hc.entity.Patient;

public interface IPatientRepository extends JpaRepository<Patient, Long>{

}
