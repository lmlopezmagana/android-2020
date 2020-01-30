package com.salesianostriana.dam.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.users.GetUserDto;
import com.salesianostriana.dam.users.UserEntity;
import com.salesianostriana.dam.users.UserEntityRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserEntityRepository userEntityRepository;

	@GetMapping("/auth/login")
	public GetUserDto login(@AuthenticationPrincipal UserEntity user) {
		return GetUserDto.builder().username(user.getUsername()).role(user.getRole()).avatar(user.getAvatar())
				.fullName(user.getFullName()).email(user.getEmail()).build();
	}

	@GetMapping("/users")
	public List<GetUserDto> list() {
		return userEntityRepository.findAll().stream()
				.map(user -> GetUserDto.builder().username(user.getUsername()).role(user.getRole())
						.avatar(user.getAvatar()).fullName(user.getFullName()).email(user.getEmail()).build())
				.collect(Collectors.toList());
	}

}
