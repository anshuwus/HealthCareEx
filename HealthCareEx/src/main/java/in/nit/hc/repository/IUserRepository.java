package in.nit.hc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.hc.entity.User;

public interface IUserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);
}
