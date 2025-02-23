package com.patton.pkg.chms.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TODO_TBL")
public class Todo {

	private static Logger LOGGER = LoggerFactory.getLogger(Todo.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "todo_id")
	private long id;

	@Column(name = "name")
	private String todoName;

	@Column(name = "user")
	private String userName;

	@Column(name = "userEmail")
	private String userEmail;

	@Column(name = "startDate")
	private String startDate;

	@Column(name = "endDate")
	private String endDate;

	@Column(name = "priority")
	private String priority;

	@Column(name = "shortDescription")
	private String shortDescription;

	@Column(name = "longDescription")
	private String longDescription;

	@Column(name = "comments")
	private String comments;

	@Column(name = "status")
	private String status;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime createdDate;

}