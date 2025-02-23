package com.patton.pkg.chms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
//@Where(clause = "jobOrder_status <> 'InActive' ")
@Table(name = "JOBORDER_TBL")
public class JobOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "joborder_id", nullable = false)
	private Long id;

	@Column(name = "joborder_title")
	private String jobOrderTitle;

	@Column(name = "joborder_no_of_positions")
	private Integer noOfPositions;

	@Column(name = "joborder_city")
	private String city;

	@Column(name = "joborder_state")
	private String state;

	@Column(name = "joborder_priority")
	private String priority;

	@Column(name = "joborder_rate")
	private Integer rate;

	@Column(name = "joborder_yrs_of_exp")
	private Integer totalExp;

	@Column(name = "joborder_visatype")
	private String visaType;

	@Column(name = "joborder_status")
	private String jobOrderStatus;

	@Column(name = "joborder_long_description")
	private String lngDesc;

	@Column(name = "joborder_comments")
	private String comments;

	@CreationTimestamp
	@Column(name = "creation_date")
	private Date createdDate;

	@UpdateTimestamp
	@Column(name = "updated_date")
	private Date updatedDate;

//	@JsonIgnore
	@ManyToOne
//	@MapsId("clientId")
	@JoinColumn(name = "client_tbl_client_id", foreignKey = @ForeignKey(name = "job_tbl_client_tbl_fk"))
	private Client clientTbl;

	// donot delete, for getting clientId && foreign key value will be coming in
//	@Formula(value = "client_tbl_client_id")
	private long clientId;

}