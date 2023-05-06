package in.nit.hc.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.nit.hc.constants.UserRoles;
import in.nit.hc.entity.User;
import in.nit.hc.service.IUserService;
import in.nit.hc.util.UserUtil;



@Component
public class MasterAccountSetupRunner implements CommandLineRunner{
	@Value("${master.user.name}")
	private String displayName;
	
	@Value("${master.user.email}")
	private String username;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserUtil userUtil;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		if(!userService.findByUsername(username).isPresent()) {
			User user =new User();
			user.setDisplayName(displayName);
			user.setUsername(username);
			user.setPassword(userUtil.genPwd());
			user.setRole(UserRoles.ADMIN.name());
			userService.saveUser(user);
			//TODO : Email service
		}
	}

}
