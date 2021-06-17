package com.amazone.services;

import com.amazone.exception.UserNotFoundException;
import com.amazone.model.Admin;

public interface AdminServices {

	public boolean validateAdmin(String username, String password) throws UserNotFoundException;
	public Admin getAdminById(String adminid) throws UserNotFoundException;
}
