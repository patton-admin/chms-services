package com.patton.pkg.chms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patton.pkg.chms.entity.UserBucketKey;
import com.patton.pkg.chms.entity.UserBucketRelation;
import com.patton.pkg.chms.repository.UserBucketRepository;

@Service
public class UserBucketServices {

	@Autowired
	private UserBucketRepository usrBkt;

	/** to retrieve particular User by id **/
	public UserBucketRelation findById(UserBucketRelation x) {
		return usrBkt.findById(x);
	}

	public List<UserBucketRelation> retrieveAllBuckets() {
		return usrBkt.findAll();
	}

	/** to save User **/
	public UserBucketRelation save(UserBucketRelation userBucket) {
		return usrBkt.save(userBucket);
	}

	/** Delete userBucket **/
	public int removeUserBucket(UserBucketKey userBucketKey) {
		int deleteCount = 0;
		Optional<UserBucketRelation> all = usrBkt.findById(userBucketKey);
		if (all.isPresent() == true) {
			UserBucketRelation x = all.get();
			System.out.println("output" + x.getId().getBucketId() + "... userId" + x.getId().getUserId());
			// delete a record...
			deleteCount = usrBkt.deleteByIdNative(x.getId().getUserId(), x.getId().getBucketId());
			System.out.println("Deleted Successfully...");
		} else {
			System.out.println("Not Found...");
		}
		return deleteCount;
	}

	/** Delete userBucket **/
	public int unAssignUserBucket(UserBucketKey userBucketKey) {
		int updatedCount = 0;
		Optional<UserBucketRelation> all = usrBkt.findById(userBucketKey);
		if (all.isPresent() == true) {
			UserBucketRelation x = all.get();
			System.out.println("output" + x.getId().getBucketId() + "... userId" + x.getId().getUserId());
			// delete a record...
			updatedCount = usrBkt.UpdateUserByIdNative("InActive", x.getId().getUserId(), x.getId().getBucketId());
			System.out.println("Updated Successfully...");
		} else {
			System.out.println("Not Found...");
		}
		return updatedCount;
	}

	public List<UserBucketRelation> getAllUsers(Long bucketId) {
		return usrBkt.getUsersByBucketId(bucketId);
	}

}
