package com.patton.pkg.chms.entity;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * Date :July 25,2018
 * 
 * @author aravind
 * @version 1.0
 *
 */
@Getter
@Setter
@Component
public class MailUser {

	private String emailAddress;
	private String sub;
	private String body;

}