package com.salesianostriana.dam;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.salesianostriana.dam.users.UserEntity;
import com.salesianostriana.dam.users.UserEntityRepository;

@SpringBootApplication
public class SpringSecurityBasicServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicServerApplication.class, args);
	}
	
	
	
	@Bean
	public CommandLineRunner initData(UserEntityRepository repo, PasswordEncoder passwordEncoder) {
		return args -> {
			
			List<UserEntity> users = Arrays.asList(UserEntity.builder()
													.username("luismi")
													.password(passwordEncoder.encode("12345"))
													.email("luismi.lopez@salesianos.edu")
													.avatar("https://api.adorable.io/avatars/285/luismi.lopez@salesianos.edu.png")
													.fullName("Luis Miguel López Magaña")
													.role("ADMIN")
													.build(),
													UserEntity.builder()
													.username("miguel")
													.password(passwordEncoder.encode("12345"))
													.email("miguel.campos@salesianos.edu")
													.avatar("https://api.adorable.io/avatars/285/miguel.campos@salesianos.edu.png")
													.fullName("Miguel Campos Rivera")
													.role("USER")
													.build()
													);
			
			repo.saveAll(users);
			
		};
	}

}
