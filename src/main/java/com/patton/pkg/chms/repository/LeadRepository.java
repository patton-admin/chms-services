package com.patton.pkg.chms.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.patton.pkg.chms.entity.Lead;
import com.patton.pkg.chms.entity.resultset.LeadDashboardProjectionData;
import com.patton.pkg.chms.entity.resultset.LeadInf;

@Repository
@Transactional
public interface LeadRepository extends JpaRepository<Lead, Long> {

	// jpa query example...
	@Query("SELECT u.id as leadid," + "u.leadEmail as leademail, " + "u.leadPhone as leadphone,"
			+ "u.leadFirstName as leadfirstname," + "u.leadLastName as leadlastname,"
			+ "u.leadPrimaryEmail as leadprimaryemail," + "u.leadState as leadstate," + "u.leadEmpName as leadempname,"
			+ "u.leadJobTitle as leadjobtitle," + "u.leadSalary as leadsalary," + "u.leadVisaType as leadvisatype,"
			+ "u.bucket as bucket FROM Lead u WHERE u.id > ?1")

//	@Query(value = FIND_PASSWORD, nativeQuery = true)
	Collection<LeadInf> findLeadById(Long id);

	// to find Lead by leadPrimaryEmail...
	Lead findByLeadPrimaryEmail(String email);

	// softDelete the Operation in jpa...
	@Query("UPDATE Lead e SET e.leadStatus='InActive' WHERE e.id =?1")
	@Modifying
	public int deleteLeadById(Long id);

	@Query("DELETE Lead e WHERE e.id IN (?1)")
	@Modifying
	void deleteLeadByIdHard(List<Long> id);

	/**
	 * to get the chartData for the Lead...
	 **/

	@Query(value = "select a.lead_id, " + "b.bucket_id,  " + "a.lead_submitted_job_id, " + "b.bucket_name, "
			+ "a.lead_firstname,  " + "a.lead_lastname, " + "a.lead_state, " + "a.lead_visatype, "
			+ "a.lead_practice_area, " + "a.lead_Status, " + "a.lead_yrs_of_exp,  " + "a.creation_date"
			+ " from lead_tbl a,  bucket_tbl b "
			+ "where a.bucket_tbl_bucket_id = b.bucket_id and a.creation_date >= ?1 and a.creation_date <= ?2 order by b.bucket_id;", nativeQuery = true)
	List<LeadDashboardProjectionData> filterLeadOnlyByDate(String startDate, String endDate);

	@Query(value = "select a.lead_id," + "b.bucket_id," + "a.lead_submitted_job_id," + "b.bucket_name,"
			+ "a.lead_firstname," + "a.lead_lastname," + "a.lead_state," + "a.lead_visatype," + "a.lead_practice_area,"
			+ "a.lead_status," + "a.lead_yrs_of_exp," + "a.creation_date" + " from lead_tbl a,  bucket_tbl b "
			+ "where a.bucket_tbl_bucket_id = b.bucket_id and a.creation_date >= ?1 and a.creation_date <= ?2 and b.bucket_id = ?3  order by b.bucket_id;", nativeQuery = true)
	List<LeadDashboardProjectionData> filterleadByDateAndBucketId(String startDate, String endDate, Long bucketId);
}
