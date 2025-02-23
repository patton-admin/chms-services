package com.patton.pkg.chms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.patton.pkg.chms.entity.Bucket;

/**
 * @author AM
 *
 */
@Repository
public interface BucketRepository extends JpaRepository<Bucket, Long> {

	Bucket findByBucketName(String name);

	// softDelete the Operation in jpa...
	@Query("UPDATE Bucket e SET e.bucketStatus='InActive' WHERE e.id =?1")
	@Modifying
	public int deleteBucketById(Long id);

	// named query example...from bucket table
	List<Bucket> findByBucketOwner(String bucketOwner);

	@Query(value = "SELECT lead_id e FROM lead_tbl e WHERE e.bucket_tbl_bucket_id IN (?1)", nativeQuery = true)
	@Modifying
	List<Long> getAllLeadsByBucketId(List<Long> id);

	@Query(value = "SELECT client_id e FROM client_tbl e WHERE e.bucket_tbl_bucket_id IN (?1)", nativeQuery = true)
	@Modifying
	List<Long> getAllClientsByBucketId(List<Long> id);

	@Query("DELETE Bucket e WHERE e.id IN (?1)")
	@Modifying
	void deleteBucketByIdHard(List<Long> id);

}
