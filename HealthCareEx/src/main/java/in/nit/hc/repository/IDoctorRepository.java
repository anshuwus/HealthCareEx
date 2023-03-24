package in.nit.hc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.hc.entity.Doctor;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {

}
