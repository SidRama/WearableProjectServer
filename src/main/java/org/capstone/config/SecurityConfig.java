package org.capstone.config;

import org.capstone.security.AuthenticatedUser;
import org.capstone.entities.User;
import org.capstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)

public class SecurityConfig extends GlobalAuthenticationConfigurerAdapter {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Autowired
			UserRepository userRepository;
			
			@Override
			@Transactional
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				User user = null;
				try{
				user = userRepository.findByPhonenumber(username);
				}
				catch(Exception e) {
					throw new UsernameNotFoundException("could not find the user " + username);
				}
				if (user == null){
					throw new UsernameNotFoundException("could not find the user " + username);
				}
				
				return new AuthenticatedUser(user);
			}
		
	};
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService())
			.passwordEncoder(passwordEncoder());			
	}

/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
	*/
	

	@Configuration
	@Order(1)                                                        
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
	
		protected void configure(HttpSecurity http) throws Exception {
			/*http
			.antMatcher("/api").authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.authorizeRequests()
				.antMatchers("/app/login").permitAll()
			.and()
			.authorizeRequests()
				.antMatchers("/register").permitAll()
			.and()
			.httpBasic()
			.and()
				.antMatcher("/api/**")
					.authorizeRequests()
						.anyRequest().authenticated()
						.and()
					.httpBasic()
						.and()
					.csrf()
						.disable();
*/
			
			http
			.antMatcher("/api/**")
			.authorizeRequests()
				.anyRequest().authenticated()
			.and()
			.authorizeRequests()
				.antMatchers("/app/**").permitAll()	
				.and()
			.httpBasic()
				.and()
			.csrf()
				.disable();
		}

	}
	

	@Configuration                                                  
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.csrf()
					.disable() // This disables CSRF protection for everything, including the web part!
					           // I couldn't figure out how to do it only for the REST part. If someone figures
					           // this out, please do it.  --Ankit
				.authorizeRequests() 
					.antMatchers("/","/register","/hello","/app/register","/app/**").permitAll()
					.anyRequest().authenticated()
					.and()
				.formLogin()
					.loginPage("/login")
					.permitAll()
					.and()
				.logout()
					.permitAll();
		}

	}
}