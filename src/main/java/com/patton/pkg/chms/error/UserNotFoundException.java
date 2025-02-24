package com.patton.pkg.chms.error;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(Long id) {
		super("User id not found: " + id);
	}
}
