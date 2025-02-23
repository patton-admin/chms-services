package com.patton.pkg.chms.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class JobOrderData {

	private static Logger LOGGER = LoggerFactory.getLogger(JobOrderData.class);

	private String id;

	private String jobOrderTitle;

	private String noOfPositions;

	private String city;

	private String state;

	private String priority;

	private Integer rate;

	private Integer totalExp;

	private String visaType;

	private String jobOrderStatus;

	private int jobOrderClientId;

	private String jobOrderlngDesc;

	private String comments;

}