package com.formation.mvc.services.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.formation.mvc.entities.UserEntity;
import com.formation.mvc.repositorys.UserRepository;
import com.formation.mvc.services.UserService;
import com.formation.mvc.shared.Utils;
import com.formation.mvc.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		userEntity.setEncreptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userEntity.setUserId(utils.generateStringId(32));
		UserEntity userCreated = userRepository.save(userEntity);
		
		UserDto userCreatedDto = modelMapper.map(userCreated, UserDto.class);
		
		return userCreatedDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserEntity>  usersEntity = userRepository.findAll();
		
		Type  listTyp = new TypeToken<List<UserDto>>() {}.getType();
		List<UserDto> usersDto = new ModelMapper().map(usersEntity, listTyp);
		
		return usersDto;
	}

	@Override
	public UserDto updateUser(UserDto userDto, String userId) {
	UserEntity userEntity	= userRepository.findByUserId(userId);
	if(userEntity == null) new RuntimeException("user not found !! ");
		
	userEntity.setFirstName(userDto.getFirstName());
	userEntity.setLastName(userDto.getLastName());
	userEntity.setEmail(userDto.getEmail());
	userEntity.setEncreptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
	
	UserEntity userUpdatedEntity = userRepository.save(userEntity);
	ModelMapper modelMapper =  new ModelMapper();
	UserDto userUpdatedDto = modelMapper.map(userUpdatedEntity, UserDto.class);
	
		return userUpdatedDto;
	}



	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(), userEntity.getEncreptedPassword(), new ArrayList<>());
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) throw new RuntimeException("user not found !!");
		
		userRepository.delete(userEntity);
		
	}

}
