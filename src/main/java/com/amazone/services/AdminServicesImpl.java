package com.amazone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazone.exception.UserNotFoundException;
import com.amazone.repository.AdminDAO;

@Service
public class AdminServicesImpl implements AdminServices {

	@Autowired
	AdminDAO adminDAO;
	
	@Override
	public boolean validateAdmin(String username, String password) throws UserNotFoundException {
		boolean result = adminDAO.existsByAdminIdAndPassword(username, password);
		if(!result)
			throw new UserNotFoundException("User Not Found");
		return result;
	}

}
