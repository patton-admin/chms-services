package com.patton.pkg.chms.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class BucketUserData {

	private Long userId;

	private Long bucketId;

}
