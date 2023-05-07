package in.nit.hc.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.nit.hc.constants.UserRoles;
import in.nit.hc.entity.User;
import in.nit.hc.service.IUserService;
import in.nit.hc.util.MyMailUtil;
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
	
	@Autowired
	private MyMailUtil mailUtil;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("MasterAccountSetupRunner.run():: 1");
		if(!userService.findByUsername(username).isPresent()) {
			String pwd = userUtil.genPwd();
			User user =new User();
			user.setDisplayName(displayName);
			user.setUsername(username);
			user.setPassword(pwd);
			user.setRole(UserRoles.ADMIN.name());
			Long genId = userService.saveUser(user);
			System.out.println("MasterAccountSetupRunner.run():: "+genId);
			if(genId!=null) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						System.out.println("MasterAccountSetupRunner.run(...).new Runnable() {...}.run()");
						String text = "Your name is "+username+", password is "+pwd;
						mailUtil.send(username, "ADMIN ADDED", text);
					}
				}).start();
			}
			
		}
	}

}
