package in.nit.hc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nit.hc.entity.Doctor;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
	//for dynamic dropdown
	@Query("SELECT id,firstName,lastName,specialization.specName FROM Doctor")
	List<Object[]> getDocIdAndName();
	
	//finding a doctor based on specialization selected
	//SQL queries
	/*select doc.*
	from doctor_tab doc
	    inner join
	    specialization_tab spc
	on doc.spec_id_fk_col = spc.spec_id_col
	where spc.spec_name_col like 'Orthodontics';*/
	//JPQL queries
	/*
	SELECT doc.firstName,doc.lastName
	FROM Doctor doc
	    inner join
	    doc.specialization spc
	WHERE spc.id like :input;
	*/
	@Query("SELECT  doc FROM Doctor doc INNER JOIN doc.specialization spc WHERE spc.id like :id")
	List<Doctor> findDoctorBySpecId(Long id);
	
}
