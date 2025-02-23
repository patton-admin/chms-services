package com.patton.pkg.chms.error;

public class ClientNotFoundException extends RuntimeException {
	
	public ClientNotFoundException(Long id) {
		super("Client id not found: " + id);
	}
}
