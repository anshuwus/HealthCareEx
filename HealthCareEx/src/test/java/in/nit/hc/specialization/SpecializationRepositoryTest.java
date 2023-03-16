package in.nit.hc.specialization;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import in.nit.hc.entity.Specialization;
import in.nit.hc.repository.ISpecializationRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false) 
public class SpecializationRepositoryTest {
	@Autowired
	private ISpecializationRepository repo;
	
	/*1. Test save operation*/
	@Test
	@Order(1)
	public void testSpecCreate() {
		Specialization spec=new Specialization(null, "CRDLS", "Cardiologists", "A cardiologist is a healthcare provider");
		spec =repo.save(spec);
		assertNotNull(spec.getId(),"Spec is not created");
	}
	
	/*2. Test display all operation*/
	@Test
	@Order(2)
	public void testSpecFetchAll() {
		List<Specialization> list=repo.findAll();
		assertNotNull(list);
		//assertThat(list.size()>0);
	}
}
