package com.patton.pkg.chms.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
//@Where(clause = "client_status <> 'InActive' ")
@Table(name = "CLIENT_TBL")
public class Client {

	private static Logger LOGGER = LoggerFactory.getLogger(Client.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "client_id")
	private long clientId;

	@Column(name = "client_name")
	private String clientName;

	@Column(name = "client_contactperson")
	private String contactPerson;

	@Column(name = "client_email")
	private String clientEmail;

	@Column(name = "client_vendorname")
	private String vendorName;

	@Column(name = "client_vendoremail")
	private String vendorEmail;

	@Column(name = "client_status")
	private String clientStatus;

	@Column(name = "client_vendorcontactperson")
	private String vendorContactPerson;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@ManyToOne
	@JoinColumn(name = "bucket_tbl_bucket_id")
	private Bucket bcktClients;

	@JsonIgnore
	@OneToMany(mappedBy = "clientTbl")
	private List<JobOrder> jobOrder;

	public void addJobOrder(JobOrder jobOrder) {
		this.jobOrder.add(jobOrder);
	}

	@Formula(value = "bucket_tbl_bucket_id")
	private Long bucketId;

}