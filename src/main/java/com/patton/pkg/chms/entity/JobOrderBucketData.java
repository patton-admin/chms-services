package com.patton.pkg.chms.entity;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class JobOrderBucketData {

	private String startDate;

	private String endDate;

	private Long bucketId;

}
