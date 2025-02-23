package com.patton.pkg.chms.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_bucket_rel")
public class UserBucketRelation {

	@EmbeddedId
	private UserBucketKey id = new UserBucketKey();

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(foreignKey = @ForeignKey(name = "user_bucket_rel_user_tbl_fk"))
	private UserTbl userTbl;

	@ManyToOne
	@MapsId("bucketId")
	@JoinColumn(foreignKey = @ForeignKey(name = "user_bucket_rel_bucket_tbl_fk"))
	private Bucket bucketTbl;

	private String status;
}
