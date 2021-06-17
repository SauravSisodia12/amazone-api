package com.amazone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazone.exception.UserNotFoundException;
import com.amazone.model.Admin;
import com.amazone.repository.AdminRepository;

@Service
public class AdminServicesImpl implements AdminServices {

	@Autowired
	AdminRepository adminDAO;
	
	@Override
	public boolean validateAdmin(String username, String password) throws UserNotFoundException {
		boolean result = adminDAO.existsByAdminIdAndPassword(username, password);
		if(!result)
			throw new UserNotFoundException("User Not Found");
		return result;
	}

	@Override
	public Admin getAdminById(String adminid) throws UserNotFoundException {
		Admin admin = adminDAO.findUserByadminId(adminid);
		return admin;
	}

}
