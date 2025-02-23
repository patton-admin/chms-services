package com.patton.pkg.chms.error;

public class BucketNotFoundException extends RuntimeException{

	public BucketNotFoundException(Long id) {
		super("Bucket id not found: " + id);
	}
}
