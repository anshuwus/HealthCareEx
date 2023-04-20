package in.nit.hc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nit.hc.entity.Doctor;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
	//for dynamic dropdown
	@Query("SELECT id,firstName,lastName,specialization.specName FROM Doctor")
	List<Object[]> getDocIdAndName();
}
