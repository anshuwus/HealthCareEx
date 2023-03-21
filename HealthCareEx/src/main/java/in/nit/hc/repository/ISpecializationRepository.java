package in.nit.hc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nit.hc.entity.Specialization;

public interface ISpecializationRepository extends JpaRepository<Specialization, Long> {
	
	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode")
	Integer getSpecCodeCount(String specCode);
	//for edit check opertaion query
	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode AND id!=:id")
	Integer getSpecCodeCountForEdit(String specCode,Long id);
	
	@Query("SELECT COUNT(specName) FROM Specialization WHERE specName=:specName")
	Integer getSpecNameCount(String specName);
	@Query("SELECT COUNT(specName) FROM Specialization WHERE specName=:specName AND id!=:id")
	Integer getSpecNameCountForEdit(String specName,Long id);

}
