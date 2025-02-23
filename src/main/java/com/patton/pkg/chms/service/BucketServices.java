package com.patton.pkg.chms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patton.pkg.chms.entity.Bucket;
import com.patton.pkg.chms.entity.Lead;
import com.patton.pkg.chms.entity.UserTbl;
import com.patton.pkg.chms.error.BucketNotFoundException;
import com.patton.pkg.chms.repository.BucketRepository;

@Service
public class BucketServices {

	@Autowired
	BucketRepository bkt;

	@Autowired
	LeadServices ld;

	@Autowired
	ClientServices cli;

	public List<Bucket> retrieveAllBuckets() {
		return bkt.findAll();
	}

	/** to retrieve particular Bucket by id **/
	public Bucket findById(Long id) {
		Bucket Bucket = bkt.findById(id).orElseThrow(() -> new BucketNotFoundException(id));
		return Bucket;
	}

	/** to retrieve all leads of Bucket by id **/
	public List<Lead> findAllLeads(Long id) {
		Bucket bucket = bkt.findById(id).orElseThrow(() -> new BucketNotFoundException(id));
		return bucket.getLeads();
	}

	public List<UserTbl> findAllUsers(Long id) {
		Bucket bucket = bkt.findById(id).orElseThrow(() -> new BucketNotFoundException(id));
//		return bucket.getUser();
		return null;
	}

	public Bucket findByBucketName(String name) {
		Bucket bucket = bkt.findByBucketName(name);
		return bucket;
	}

	/** to save Bucket **/
	public Bucket save(Bucket bucket) {
		return bkt.save(bucket);
	}

	/** to delete Bucket **/
	public void deleteById(Long id) {
		bkt.findById(id).orElseThrow(() -> new BucketNotFoundException(id));
		bkt.deleteById(id);
	}

	/**
	 * @namedqeruy example
	 */

	public List<Bucket> bucketByOwner(String owner) {
		List<Bucket> buckets = bkt.findByBucketOwner(owner);
		return buckets;
	}

	@Transactional
	public int deleteBktById(Long id) {
		int deletedRowCount = bkt.deleteBucketById(id);
		return deletedRowCount;
	}

	@Transactional
	/** to delete bucket and its children's **/
	public void deleteBuckets(List<Long> ids) {

		// lead Deletion completed...
		List<Long> leadIds = bkt.getAllLeadsByBucketId(ids);
		ld.deleteLead(leadIds);

		// clients Deletion Completed...
		List<Long> clientIds = bkt.getAllClientsByBucketId(ids);
		cli.deleteById(clientIds);

		// Buckets Deletion Completed...
		bkt.deleteBucketByIdHard(ids);
	}

}
