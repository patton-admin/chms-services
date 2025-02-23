package com.patton.pkg.chms.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Where(clause = "user_status <> 'InActive' ")
@Table(name = "user_tbl")
public class UserTbl {

	private static Logger LOGGER = LoggerFactory.getLogger(UserTbl.class);

	@Id
	@GeneratedValue
	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "user_loginid")
	private String userLoginId;

	@Column(name = "user_password", nullable = false)
	private String userPassword;

	@Column(name = "user_firstname")
	private String userFirstName;

	@Column(name = "user_lastname")
	private String userLastName;

	@Column(name = "user_phone_primary")
	private String userPrimaryPhone;

	@Column(name = "user_phone_secondary")
	private String userSecondaryPhone;

	@Column(name = "user_role")
	private String userRole;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_location")
	private String userLocation;

	@Column(name = "user_resetquestion1")
	private String resetQuestion1;

	@Column(name = "user_resetquestion2")
	private String resetQuestion2;

	@Column(name = "user_resetans1")
	private String resetans1;

	@Column(name = "user_resetans2")
	private String resetans2;

	@Column(name = "user_createdby")
	private String createdBy;

	@Column(name = "user_updatedby")
	private String updatedBy;

	@Column(name = "user_status")
	private String userStatus;

	@CreationTimestamp
	@Column(name = "user_createdtimestamp")
	private Date createdTimeStamp;

	@UpdateTimestamp
	@Column(name = "user_updatedtimestamp")
	private Date updatedTimeStamp;

	@UpdateTimestamp
	@JsonProperty("lastUpdatedDate")
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	@JsonProperty("createdDate")
	private LocalDateTime createdDate;

	// new way of mapping for n-n mapping...
	@OneToMany(mappedBy = "bucketTbl", cascade = CascadeType.REMOVE)
	private Set<UserBucketRelation> userBuckets = new HashSet<>();
}
