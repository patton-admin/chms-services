package com.patton.pkg.chms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserBucketKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_tbl")
	private Long userId;

	@Column(name = "bucket_tbl")
	private Long bucketId;

}
