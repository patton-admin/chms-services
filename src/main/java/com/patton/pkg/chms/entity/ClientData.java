package com.patton.pkg.chms.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ClientData {

	private static Logger LOGGER = LoggerFactory.getLogger(ClientData.class);

	private Long clientId;

	private String clientName;

	private String contactPerson;

	private String clientEmail;

	private String vendorName;

	private String vendorEmail;

	private String vendorContactPerson;

	private Long bucketId;

}