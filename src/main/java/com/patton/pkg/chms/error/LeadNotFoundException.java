package com.patton.pkg.chms.error;

public class LeadNotFoundException extends RuntimeException {

	public LeadNotFoundException(Long id) {
		super("Lead id not found: " + id);
	}
}
