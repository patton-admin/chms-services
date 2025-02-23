package com.patton.pkg.chms.controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patton.pkg.chms.entity.Bucket;
import com.patton.pkg.chms.entity.BucketUserData;
import com.patton.pkg.chms.entity.UserBucketKey;
import com.patton.pkg.chms.entity.UserBucketRelation;
import com.patton.pkg.chms.entity.UserTbl;
import com.patton.pkg.chms.service.BucketServices;
import com.patton.pkg.chms.service.UserBucketServices;
import com.patton.pkg.chms.service.UserServices;

@RestController
@RequestMapping("/chms")
public class UserBucketController {

	@Autowired
	private BucketServices bkt;

	@Autowired
	private UserServices usr;

	@Autowired
	private UserBucketServices usrBkt;

	@Autowired
	private EntityManager em;

	private static Logger logger = LoggerFactory.getLogger(UserBucketController.class);

	@GetMapping("/getBucketRelation")
	public List<UserBucketRelation> getBucketRelation() {
		return usrBkt.retrieveAllBuckets();
	}

	@PostMapping(path = "/assignBucket", consumes = "application/json", produces = "application/json")
	public UserBucketRelation assignBucket(@RequestBody UserBucketRelation bktUser) {
		bktUser.setStatus("Active");
		UserBucketRelation saved = usrBkt.save(bktUser);
		return saved;
	}

	// persisting n-n mapping...
	@PostMapping(path = "/assignBucketUser", consumes = "application/json", produces = "application/json")
	public void assignBucketToUser(@RequestBody BucketUserData bktUser) {

		UserTbl tbl = usr.findById(bktUser.getUserId());
		Bucket bucket = bkt.findById(bktUser.getBucketId());

		UserBucketRelation x = new UserBucketRelation();
		x.setBucketTbl(bucket);
		x.setUserTbl(tbl);

//		UserBucketRelation isAvailable = usrBkt.findById(x);	

		if (tbl == null || bucket == null) {
			throw new IllegalArgumentException("Please Provide Proper Bkt-User data...");
		} else {
			System.out.println("tbl  :" + tbl.getUserId());
			System.out.println("bucket :" + bucket.getId());

			UserBucketRelation ubr = new UserBucketRelation();
			ubr.setStatus("Active");
			ubr.setBucketTbl(bucket);
			ubr.setUserTbl(tbl);
			tbl.getUserBuckets().add(ubr);
			bucket.getUserBuckets().add(ubr);
			logger.info("ubr -> {}", ubr);
			usrBkt.save(ubr);
		}

	}

	@PostMapping(path = "/unAssignBucket", consumes = "application/json", produces = "application/json")
	public int unAssignBucket(@RequestBody BucketUserData bktUserData) {

		UserTbl tbl = usr.findById(bktUserData.getUserId());
		Bucket bucket = bkt.findById(bktUserData.getBucketId());

		System.out.println("table user : " + tbl.getUserId());
		System.out.println("Bucket user : " + bucket.getId());

		UserBucketKey x = new UserBucketKey();
		x.setUserId(tbl.getUserId());
		x.setBucketId(bucket.getId());

		int count = usrBkt.unAssignUserBucket(x);
		return count;
	}

	@PostMapping(path = "/getAllUsersByBucketId", consumes = "application/json", produces = "application/json")
	public List<UserBucketRelation> getAllUsersByBucketId(@RequestBody UserBucketKey bktUser) {
		System.out.println("value that is going in are..." + bktUser.getBucketId());
		List<UserBucketRelation> saved = usrBkt.getAllUsers(bktUser.getBucketId());
		return saved;
	}

	/**
	 * For AllCandidate page : write a query: get bucket where userid = "hisuserid"
	 * For MyCandidate page: same with extra condition ownerid = "hisuserid"
	 */

	@PostMapping(path = "/getUsrBckt", consumes = "application/json", produces = "application/json")
	public UserBucketRelation getUserBucketRel(@RequestBody UserBucketRelation bktUser) {
		UserBucketRelation saved = usrBkt.findById(bktUser);
		return saved;
	}

}
