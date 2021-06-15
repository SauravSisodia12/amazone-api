package com.amazone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazone.model.Product;
import com.amazone.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

	boolean existsByUserIdAndPassword(String name,String password);
	User findUserByUserId(String userId);
 
}
