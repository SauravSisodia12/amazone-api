package com.amazone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amazone.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByUserIdAndPassword(String name,String password);
	User findUserByUserId(String userId);
	
}
