package com.amazone.services;

import com.amazone.exception.UserNotFoundException;

public interface AdminServices {

	public boolean validateAdmin(String username, String password) throws UserNotFoundException;
}
