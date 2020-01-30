package com.salesianostriana.dam.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final AccessDeniedHandler accessDeniedHandler;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()
			.authenticationEntryPoint(customBasicAuthenticationEntryPoint)
			.and()
			.authorizeRequests()
				.anyRequest().authenticated()
			.and()
				.csrf().disable();	
		
		
		http.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);
		
		
	}
	
}
