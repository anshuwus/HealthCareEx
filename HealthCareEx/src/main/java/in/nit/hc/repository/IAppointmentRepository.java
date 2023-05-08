package in.nit.hc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.hc.entity.Appointment;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long>{
	
	//Get all appointment of given doctor id
	//SQL Queries
	/*
	 select app.*	
	 from appointment_tab app 
	     inner join
	     doctor_tab as doc
	 on doc.doc_id_col = app.id
	 where doc.doc_id_col=1;
	 */
	//JPQL-HQL Queires 
	/*
	SELECT app
	FROM appointment app 
	    INNER JOIN
	    app.doctor doc
	WHERE doc.id =: id
	*/
}
