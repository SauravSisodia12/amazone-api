package com.amazone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazone.model.Admin;

@Repository
public interface AdminDAO extends JpaRepository<Admin, Integer> {

	boolean existsByAdminIdAndPassword(String name,String password);
}
