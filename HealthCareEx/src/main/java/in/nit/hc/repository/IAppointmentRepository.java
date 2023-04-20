package in.nit.hc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.hc.entity.Appointment;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long>{

}
