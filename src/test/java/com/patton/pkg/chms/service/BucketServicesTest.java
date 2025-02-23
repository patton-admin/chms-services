package com.patton.pkg.chms.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.patton.pkg.chms.entity.Bucket;
import com.patton.pkg.chms.entity.Lead;
import com.patton.pkg.chms.entity.UserTbl;
import com.patton.pkg.chms.error.BucketNotFoundException;

//hamcrest...
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class BucketServicesTest {

	@Autowired
	BucketServices bkt;

	@Test
	public void findBy_Invalid_BucketId() {
		Assertions.assertThrows(BucketNotFoundException.class, () -> {
			bkt.findById(402L);
		});
	}

	@Test
	public void findBy_Valid_BucketId() {
		Bucket bucket = bkt.findById(42L);
		assertThat(bucket.getId()).isEqualTo(42L);
	}

	@Test
	public void find_All_Buckets() {
		List<Bucket> bucket = bkt.retrieveAllBuckets();
		assertThat(bucket).asList();
	}

	/****
	 * Scenario for getting the leads based on BucketId
	 */
	@Test
	public void find_All_Leads_ByBucketId() {
		List<Lead> ld = bkt.findAllLeads(42L);
		assertThat(ld).asList();
	}

	@Test
	public void find_All_Users_ByBucketId() {
		List<UserTbl> ld = bkt.findAllUsers(42L);
		assertThat(ld).asList();
	}

	@Test
	public void test_save_new_Bucket() {
		Bucket bt = new Bucket();
		bt.setBucketName("Test Bucket");
		bt.setBucketOwner("Test Owner");
		bt.setBktShrtDesc("Unit Testing Shrt");
		bt.setBktLngDesc("Unit Testing Long Description");
		Bucket newBkt1 = bkt.save(bt);
		Bucket newBkt = bkt.findByBucketName("Test Bucket");
		assertThat(newBkt.getId()).isEqualTo(newBkt1.getId());
	}

	@Test
	public void test_delete_new_Bucket() {
		Bucket newBkt = bkt.findByBucketName("Test Bucket");
		bkt.deleteById(newBkt.getId());
	}
	
	/**
	 * @testing named query 
	 * **/
	@Test
	public void testing_named_query() {
		List<Bucket> buckets = bkt.bucketByOwner("Custom");
		assertThat(buckets).asList();
		assertThat(buckets, contains(
		          hasProperty("bucketName", equalTo("Custom-BUCKET")),
		          hasProperty("bucketName", equalTo("Custom-BUCKET")),
		          hasProperty("bucketName", equalTo("Custom-BUCKET")),
		          hasProperty("bucketName", equalTo("Custom-BUCKET")),
		          hasProperty("bucketOwner", equalTo("Custom"))
		        ));
	}
	
}
