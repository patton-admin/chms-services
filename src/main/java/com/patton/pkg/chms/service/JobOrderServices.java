package com.patton.pkg.chms.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patton.pkg.chms.entity.Client;
import com.patton.pkg.chms.entity.JobOrder;
import com.patton.pkg.chms.entity.resultset.JobOrderProjectionData;
import com.patton.pkg.chms.repository.JobOrderRepository;

@Service
public class JobOrderServices {

	@Autowired
	JobOrderRepository jo;

	@Autowired
	EntityManager em;

	/** to retrieve all Lead **/
	public List<JobOrder> retrieveAllJobOrders() {
		return jo.findAll();
	}

	/** to save Lead **/
	public JobOrder save(JobOrder jobOrder) {
		JobOrder order = jo.save(jobOrder);
		return order;
	}

	/***
	 * to update the existing jobOrder...
	 */
	@Transactional
	public JobOrder update(JobOrder jobOrder) {
		JobOrder order = jo.findBy_JobOrderId(jobOrder.getId());
		JobOrder job = null;
		if (order != null) {
			Client client = order.getClientTbl();
			jobOrder.setClientTbl(client);
			job = em.merge(jobOrder);
		}
		return job;
	}

	/** to delete Lead - soft Delete Example **/
	@Transactional
	public int deleteJobOrder(Long id) {
		int deletedRowCount = jo.deleteJobOrderById(id);
		return deletedRowCount;
	}

	/** to delete Lead - hard Delete Example **/
	@Transactional
	public void deleteJobOrderByHard(List<Long> jobId) {
		jo.deleteJobOrder(jobId);

	}

	public List<Long> getAllJobOrders(List<Long> id) {
		List<Long> jobOrderIds = jo.getJobOrders(id);
		return jobOrderIds;
	}

	public List<JobOrderProjectionData> getDashboardData(String startDate, String endDate, Long bucketId) {

		if (!startDate.equals("empty") && !endDate.equals("empty") && bucketId > 0) {
			return jo.jobDataByDateAndBucketId(startDate, endDate, bucketId);
		} else if (startDate.equals("empty") && endDate.equals("empty") && bucketId > 0) {
			return jo.filterJobDataByBucketId(bucketId);
		} else if (!startDate.equals("empty") && endDate.equals("empty") && bucketId > 0) {
			return jo.filterStartDateAndBucketId(startDate, bucketId);
		} else if (startDate.equals("empty") && !endDate.equals("empty") && bucketId > 0) {
			return jo.filterEndDateAndBucketId(endDate, bucketId);
		} else if (!startDate.equals("empty") && !endDate.equals("empty") && bucketId < 0) {
			return jo.filterOnlyByDate(startDate, endDate);
		} else {
			return null;
		}

	}

}
