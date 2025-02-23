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
public class JobOrderKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "joborder_id")
	private Long jobOrderId;

	@Column(name = "client_id")
	private String clientId;

}
