package com.formation.mvc.controllers;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.mvc.requests.UserRequest;
import com.formation.mvc.responses.UserResponse;
import com.formation.mvc.services.UserService;
import com.formation.mvc.shared.dto.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest){
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		UserDto createdUser = userService.createUser(userDto);
	    UserResponse userResponse =	modelMapper.map(createdUser, UserResponse.class);
		
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUsers(){
		List<UserDto> usersDto = userService.getAllUsers();
		Type  listTyp = new TypeToken<List<UserResponse>>() {}.getType();
		List<UserResponse> usersResponse = new ModelMapper().map(usersDto, listTyp);
		
		return new ResponseEntity<List<UserResponse>>(usersResponse,HttpStatus.OK);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest, @PathVariable(name="id") String userId){
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		UserDto userUpdated = userService.updateUser(userDto,userId);
		UserResponse userResponse = modelMapper.map(userUpdated, UserResponse.class);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(name ="id") String userId) {
		userService.deleteUser(userId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	
	}
	

}
