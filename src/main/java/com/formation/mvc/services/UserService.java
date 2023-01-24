package com.formation.mvc.services;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.formation.mvc.shared.dto.UserDto;


public interface UserService extends UserDetailsService{
	
	UserDto createUser(UserDto userDto);
	List<UserDto> getAllUsers();
	UserDto updateUser(UserDto userDto, String userId);
	UserDto getUser(String email);
	void deleteUser(String userId);

}
