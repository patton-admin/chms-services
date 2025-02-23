package com.patton.pkg.chms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.patton.pkg.chms.entity.JobOrder;
import com.patton.pkg.chms.entity.resultset.JobOrderProjectionData;

@Repository
@Transactional
public interface JobOrderRepository extends JpaRepository<JobOrder, Long> {

	@Query(value = "SELECT * FROM joborder_tbl u WHERE u.joborder_id = :id", nativeQuery = true)
	JobOrder findBy_JobOrderId(@Param("id") Long id);

	// softDelete the Operation in jpa...
	@Query("UPDATE JobOrder e SET e.jobOrderStatus='InActive' WHERE e.id =?1")
	@Modifying
	public int deleteJobOrderById(Long id);

	@Query("DELETE FROM JobOrder e WHERE e.id IN (?1)")
	@Modifying
	void deleteJobOrder(List<Long> jobId);

	@Query(value = "SELECT joborder_id as id  FROM joborder_tbl e WHERE e.client_tbl_client_id IN (?1)", nativeQuery = true)
	@Modifying
	List<Long> getJobOrders(List<Long> id);

	/**
	 * Filter Data...Dashboard
	 */
	@Query(value = "SELECT a.joborder_id,a.joborder_title,a.client_tbl_client_id,a.joborder_priority,a.joborder_city,a.joborder_state,a.creation_date, b.client_name,b.bucket_tbl_bucket_id FROM joborder_tbl a,"
			+ "client_tbl b where b.client_id = a.client_tbl_client_id and b.bucket_tbl_bucket_id = ?1 order by a.client_tbl_client_id;", nativeQuery = true)
	List<JobOrderProjectionData> filterJobDataByBucketId(Long id);

	@Query(value = "SELECT a.joborder_id,a.joborder_title,a.client_tbl_client_id,a.joborder_priority,a.joborder_city,a.joborder_state,a.creation_date, b.client_name,b.bucket_tbl_bucket_id FROM joborder_tbl a,"
			+ "client_tbl b where b.client_id = a.client_tbl_client_id and a.creation_date >= ?1 and a.creation_date <= ?2 and b.bucket_tbl_bucket_id = ?3 order by a.client_tbl_client_id;", nativeQuery = true)
	List<JobOrderProjectionData> jobDataByDateAndBucketId(String startDate, String endDate, Long bucketId);

	@Query(value = "SELECT a.joborder_id,a.joborder_title,a.client_tbl_client_id,a.joborder_priority,a.joborder_city,a.joborder_state,a.creation_date, b.client_name,b.bucket_tbl_bucket_id FROM joborder_tbl a,"
			+ "client_tbl b where b.client_id = a.client_tbl_client_id and a.creation_date >= ?1 and b.bucket_tbl_bucket_id = ?2 order by a.client_tbl_client_id;", nativeQuery = true)
	List<JobOrderProjectionData> filterStartDateAndBucketId(String startDate, Long bucketId);

	@Query(value = "SELECT a.joborder_id,a.joborder_title,a.client_tbl_client_id,a.joborder_priority,a.joborder_city,a.joborder_state,a.creation_date, b.client_name,b.bucket_tbl_bucket_id FROM joborder_tbl a,"
			+ "client_tbl b where b.client_id = a.client_tbl_client_id and a.creation_date >= ?1 and b.bucket_tbl_bucket_id = ?2 order by a.client_tbl_client_id;", nativeQuery = true)
	List<JobOrderProjectionData> filterEndDateAndBucketId(String endDate, Long bucketId);

	@Query(value = "SELECT a.joborder_id,a.joborder_title,a.client_tbl_client_id,a.joborder_priority,a.joborder_city,a.joborder_state,a.creation_date, b.client_name,b.bucket_tbl_bucket_id FROM joborder_tbl a,"
			+ "client_tbl b where b.client_id = a.client_tbl_client_id and a.creation_date >= ?1 and a.creation_date <= ?2 order by a.client_tbl_client_id;", nativeQuery = true)
	List<JobOrderProjectionData> filterOnlyByDate(String startDate, String endDate);
}
