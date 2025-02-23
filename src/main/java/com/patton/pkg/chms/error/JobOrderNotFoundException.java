package com.patton.pkg.chms.error;

public class JobOrderNotFoundException extends RuntimeException {

	public JobOrderNotFoundException(Long id) {
		super("JobOrder id not found: " + id);
	}
}
