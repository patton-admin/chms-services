package com.patton.pkg.chms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.patton.pkg.chms.entity.Bucket;
import com.patton.pkg.chms.entity.Lead;
import com.patton.pkg.chms.entity.UserTbl;
import com.patton.pkg.chms.service.BucketServices;
import com.patton.pkg.chms.service.UserBucketServices;
import com.patton.pkg.chms.service.UserServices;

@RestController
@RequestMapping("/chms")
public class BucketController {

	@Autowired
	BucketServices bkt;

	@Autowired
	UserServices user;

	@Autowired
	UserBucketServices usrBkt;

	private static Logger logger = LoggerFactory.getLogger(BucketController.class);

	/** @CorefunctionsStart */

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/allBuckets")
	public List<Bucket> getAllBuckets() {
		return bkt.retrieveAllBuckets();
	}

	@PostMapping(path = "/addBucket", consumes = "application/json", produces = "application/json")
	public Bucket saveBucket(@RequestBody Bucket bucket) {
		bucket.setBucketStatus("Active");
		Bucket bucket1 = bkt.save(bucket);
		return bucket1;
	}

	@PostMapping(path = "/deleteByBucketId", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public void removeBktById(@RequestBody List<Long> bktId) {
		bkt.deleteBuckets(bktId);
	}

	/** @Corefunctionscompleted */

	@GetMapping("/getBucketById")
	@ResponseBody
	public Bucket getBucketById(@RequestParam(required = true) Long bucketId) {
		return bkt.findById(bucketId);
	}

	@GetMapping("/getUserByBucketId")
	@ResponseBody
	public List<UserTbl> getUserBucketById(@RequestParam(required = true) Long bucketId) {
		return bkt.findAllUsers(bucketId);
	}

	@GetMapping("/getLeadsByBucket")
	@ResponseBody
	public List<Lead> getLeadByBucketId(@RequestParam(required = true) Long bucketId) {
		return bkt.findAllLeads(bucketId);
	}

	// need to fix this, if no records found then return empty array...instead of
	// null
	@GetMapping("/getBucketByOwner")
	@ResponseBody
	public List<Bucket> getAllBucketByOwner(@RequestParam(required = true) String owner) {
		return bkt.bucketByOwner(owner);
	}

}
