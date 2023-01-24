package com.formation.mvc.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formation.mvc.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	UserEntity  findByUserId(String userId);
	UserEntity  findByEmail(String email);
}
