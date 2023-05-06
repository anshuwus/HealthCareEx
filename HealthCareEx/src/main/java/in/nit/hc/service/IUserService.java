package in.nit.hc.service;

import java.util.Optional;

import in.nit.hc.entity.User;

public interface IUserService {
	Long saveUser(User user);
	Optional<User> findByUsername(String username);
}
