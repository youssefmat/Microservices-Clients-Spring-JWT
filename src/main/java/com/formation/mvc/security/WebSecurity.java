package com.formation.mvc.security;


import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.formation.mvc.services.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	private final UserService userDetaildsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	public WebSecurity(UserService userDetaildsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetaildsService = userDetaildsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		    .cors().and()
		    .csrf().disable()
		    .authorizeHttpRequests()
		    .antMatchers(HttpMethod.POST,SecurityConstants.SINGN_UP_URL)
		    .permitAll()
		    .anyRequest().authenticated()
		    .and()
		    .addFilter(getAuthenticationFilter())
		    .addFilter(new AuthorizationFilter(authenticationManager()))
		    .sessionManagement()
		    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	
	protected AuthenticationFilter getAuthenticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetaildsService).passwordEncoder(bCryptPasswordEncoder);
	}
	

}
