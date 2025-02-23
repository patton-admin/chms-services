package com.patton.pkg.chms.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
//@Where(clause = "bucket_status <> 'InActive' ")
@NamedQuery(name = Bucket.findByBucketOwner, query = "SELECT " + "a from Bucket a where a.bucketOwner = :"
		+ Bucket.param_findByBucketOwner)
@Table(name = "BUCKET_TBL")
public class Bucket {

	public static final String findByBucketOwner = "Bucket.bucketOwner";
	public static final String param_findByBucketOwner = "bucketOwner";

	private static Logger LOGGER = LoggerFactory.getLogger(Bucket.class);

	@Id
	@GeneratedValue
	@Column(name = "BUCKET_ID")
	@JsonProperty("id")
	private Long id;

	@Getter
	@Column(name = "BUCKET_NAME")
	@JsonProperty("bucketName")
	private String bucketName;

	@Column(name = "BUCKET_OWNER", nullable = false)
	@JsonProperty("bucketOwner")
	private String bucketOwner;

	@Column(name = "BUCKET_SHORTDESCRIPTION")
	@JsonProperty("bktShrtDesc")
	private String bktShrtDesc;

	@Column(name = "BUCKET_LONGDESCRIPTION")
	@JsonProperty("bktLngDesc")
	private String bktLngDesc;

	@Column(name = "ASSIGNED_USERS")
	private String assignedUsers;

	@Column(name = "BUCKET_OWNER_ID")
	private String bktOwnerId;

	@Column(name = "bucket_status")
	private String bucketStatus;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@JsonIgnore
	@OneToMany(mappedBy = "bucket")
	private List<Lead> leads;

	@JsonIgnore
	@OneToMany(mappedBy = "bcktClients")
	private List<Client> clients;

	/**
	 * The join key should be the key of the extension Table...not the key of this
	 * table...
	 ***/
//	@JsonIgnore
//	@ManyToMany(fetch=FetchType.LAZY)
//	@JoinTable(name = "user_bucket_rel", joinColumns = {@JoinColumn(name = "bucket_tbl_bucket_id")}, inverseJoinColumns = {@JoinColumn(name = "user_tbl_user_id")})
//	private List<UserTbl> user = new ArrayList<>();

	public void addLead(Lead lead) {
		this.leads.add(lead);
	}

	public void addClient(Client client) {
		this.clients.add(client);
	}

//	public void addUser(UserTbl usr) {
//		this.user.add(usr);
//	}

	// new n-n mapping...
	@JsonIgnore
	@OneToMany(mappedBy = "bucketTbl")
	private Set<UserBucketRelation> userBuckets = new HashSet<>();

}
