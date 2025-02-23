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
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
//@Where(clause = "lead_status <> 'InActive' ")
//@EntityListeners({ LeadEntityListener.class })
@Table(name = "lead_tbl")
public class Lead {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lead_id")
	private Long id;

	@Column(name = "lead_email")
	private String leadEmail;

	@Column(name = "lead_phoneno")
	private String leadPhone;

	@Column(name = "lead_firstname")
	private String leadFirstName;

	@Column(name = "lead_lastname")
	private String leadLastName;

	@Column(name = "lead_email_primary")
	private String leadPrimaryEmail;

	@Column(name = "lead_email_secondary")
	private String leadSecondaryEmail;

	@Column(name = "lead_address")
	private String leadAddress;

	@Column(name = "lead_state")
	private String leadState;

	@Column(name = "lead_city")
	private String leadCity;

	@Column(name = "lead_current_empname")
	private String leadEmpName;

	@Column(name = "lead_current_job_title")
	private String leadJobTitle;

	@Column(name = "lead_current_salary")
	private Integer leadSalary;

	@Column(name = "lead_visatype")
	private String leadVisaType;

	@Column(name = "lead_willing_to_relocate")
	private String leadWlgReLoc;

	@Column(name = "lead_prefered_location")
	private String leadPrefLoc;

	@Column(name = "lead_expected_salary")
	private Integer leadExpectedSalary;

	@Column(name = "lead_practice_area")
	private String leadPracticeArea;

	@Column(name = "lead_status")
	private String leadStatus;

	@Column(name = "lead_resume_filename")
	private String leadResFileName;

	@Column(name = "lead_resume_filepath")
	private String leadResPath;

	@Column(name = "lead_notes")
	private String leadNotes;

	@Column(name = "lead_comments")
	private String comments;

	@Column(name = "joborder_id")
	private String leadJobOrderid;

	@Column(name = "lead_submitted_job_id")
	private Integer submittedJobId;

	@Column(name = "lead_yrs_of_exp")
	private Integer totalExp;

	@CreationTimestamp
	@Column(name = "creation_date")
	private Date createdDate;

	@UpdateTimestamp
	@Column(name = "updated_date")
	private Date updatedDate;

	@ManyToOne
	/****
	 * when you want to remove the bucket json, you can use like this... and you
	 * have to include @JsonIgnore, otherwise it will throw error.
	 */
	// @JsonIgnore
	// @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bucket_tbl_bucket_id", foreignKey = @ForeignKey(name = "lead_tbl_bucket_tbl_fk"))
	private Bucket bucket;

	// should be there, donot delete...
	@Formula(value = "bucket_tbl_bucket_id")
	private Long bucketId;

	public Lead(Long id, String leadEmail, String leadPhone, String leadFirstName, String leadLastName,
			String leadPrimaryEmail, String leadSecondaryEmail, String leadAddress, String leadState, String leadCity,
			String leadEmpName, String leadJobTitle, Integer leadSalary, String leadVisaType, String leadWlgReLoc,
			String leadPrefLoc, Integer leadExpectedSalary, String leadPracticeArea, String leadStatus,
			String leadResFileName, String leadResPath, String leadNotes, String comments, String leadJobOrderid,
			Integer submittedJobId, Integer totalExp, Date createdDate, Date updatedDate) {
		super();
		this.id = id;
		this.leadEmail = leadEmail;
		this.leadPhone = leadPhone;
		this.leadFirstName = leadFirstName;
		this.leadLastName = leadLastName;
		this.leadPrimaryEmail = leadPrimaryEmail;
		this.leadSecondaryEmail = leadSecondaryEmail;
		this.leadAddress = leadAddress;
		this.leadState = leadState;
		this.leadCity = leadCity;
		this.leadEmpName = leadEmpName;
		this.leadJobTitle = leadJobTitle;
		this.leadSalary = leadSalary;
		this.leadVisaType = leadVisaType;
		this.leadWlgReLoc = leadWlgReLoc;
		this.leadPrefLoc = leadPrefLoc;
		this.leadExpectedSalary = leadExpectedSalary;
		this.leadPracticeArea = leadPracticeArea;
		this.leadStatus = leadStatus;
		this.leadResFileName = leadResFileName;
		this.leadResPath = leadResPath;
		this.leadNotes = leadNotes;
		this.comments = comments;
		this.leadJobOrderid = leadJobOrderid;
		this.submittedJobId = submittedJobId;
		this.totalExp = totalExp;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

}