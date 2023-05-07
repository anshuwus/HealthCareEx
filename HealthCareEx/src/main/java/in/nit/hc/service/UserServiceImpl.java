package in.nit.hc.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.nit.hc.entity.User;
import in.nit.hc.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService{
	@Autowired
	private IUserRepository repo;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Long saveUser(User user) {
		System.out.println("UserServiceImpl.saveUser()::2");
		//read generated pwd
		String password = user.getPassword();
		//encode password
		String encodePwd = passwordEncoder.encode(password);
		//set back to object
		user.setPassword(encodePwd);
		return repo.save(user).getId();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> opt = findByUsername(username);
		if(!opt.isPresent())
			throw new UsernameNotFoundException(username);
		else {
			User user=opt.get();
			//converting user entity class object in spring security class object
			 org.springframework.security.core.userdetails.User user2 = new org.springframework.security.core.userdetails.User(
					user.getUsername(),
					user.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
					);
			return user2;
		}
		
	}

}
 