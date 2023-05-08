package in.nit.hc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import in.nit.hc.constants.UserRoles;

@EnableWebSecurity
@Configuration
public class SecurityConfig{
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
		
	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 
		  AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);

	      http.csrf().disable().cors().disable().authorizeHttpRequests()
         // .requestMatchers("/patient/register","/patient/save").permitAll()
          //.requestMatchers("/patient/all").hasAuthority(UserRoles.ADMIN.name())
          //.requestMatchers("/doctor/**").hasAuthority(UserRoles.ADMIN.name())
          
          .anyRequest().authenticated()
		   
          .and()
          //.userDetailsService(userDetailsService)
          .formLogin()
          .defaultSuccessUrl("/spec/all",true)
          
          .and()
          .logout()
    
		  ;
		  return http.build();
	  }

}
