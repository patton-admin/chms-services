package com.patton.pkg.chms.entity;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeadEntityListener {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PrePersist
	public void LeadPrePersist(Lead ob) {
		logger.info("Listening Lead Pre Persist : ", ob.getId());
	}

	@PostPersist
	public void LeadPostPersist(Lead ob) {
		logger.info("Listening Lead Post Persist : ", ob.getId());
	}

	@PostLoad
	public void LeadPostLoad(Lead ob) {
		logger.info("Listening Lead Post Load : ", ob.getId());
	}

	@PreUpdate
	public void LeadPreUpdate(Lead ob) {
		logger.info("Listening Lead Pre Update : ", ob.getId());
	}

	@PostUpdate
	public void LeadPostUpdate(Lead ob) {
		logger.info("Listening Lead Post Update : ", ob.getId());
	}

	@PreRemove
	void onPreRemove(Lead o) {
		logger.info("Before deleting the lead...", o.getId());
	}
}
