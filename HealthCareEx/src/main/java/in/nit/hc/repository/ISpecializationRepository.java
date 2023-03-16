package in.nit.hc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.hc.entity.Specialization;

public interface ISpecializationRepository extends JpaRepository<Specialization, Long> {

}
