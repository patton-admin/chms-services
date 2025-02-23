package com.patton.pkg.chms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.patton.pkg.chms.entity.Client;
import com.patton.pkg.chms.entity.JobOrder;
import com.patton.pkg.chms.entity.JobOrderBucketData;
import com.patton.pkg.chms.entity.resultset.JobOrderProjectionData;
import com.patton.pkg.chms.service.ClientServices;
import com.patton.pkg.chms.service.JobOrderServices;

@RestController
@RequestMapping("/chms")
public class JobOrderController {

	@Autowired
	JobOrderServices jb;

	@Autowired
	ClientServices clnt;

	private static Logger logger = LoggerFactory.getLogger(JobOrder.class);

	/** @CorefunctionsStart */

	@GetMapping("/allJobOrders")
	public List<JobOrder> getAllUsers() {
		return jb.retrieveAllJobOrders();
	}

	@PostMapping(path = "/addJobOrder", consumes = "application/json", produces = "application/json")
	public ResponseEntity<JobOrder> saveJobOrder(@RequestBody JobOrder jobData) {
		long clientId = jobData.getClientId();
		logger.info("clientId...", clientId);
		Client cl = clnt.findById(clientId);

		if (cl == null) {
			return null;
		} else {

			JobOrder job = new JobOrder();
			job.setId(jobData.getId());
			job.setJobOrderStatus(jobData.getJobOrderStatus());
			job.setJobOrderTitle(jobData.getJobOrderTitle());
			job.setNoOfPositions(jobData.getNoOfPositions());
			job.setCity(jobData.getCity());
			job.setState(jobData.getState());
			job.setPriority(jobData.getPriority());
			job.setRate(jobData.getRate());
			job.setTotalExp(jobData.getTotalExp());
			job.setVisaType(jobData.getVisaType());
			job.setLngDesc(jobData.getLngDesc());
			job.setComments(jobData.getComments());
			job.setCreatedDate(jobData.getCreatedDate());
			job.setUpdatedDate(jobData.getUpdatedDate());

			cl.addJobOrder(job);
			job.setClientTbl(cl);
			JobOrder jo = jb.save(job);

			return ResponseEntity.ok(jo);
		}
	}

	@PostMapping(path = "/deleteByJobId", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public void removeJobById(@RequestBody List<Long> jobId) {
		jb.deleteJobOrderByHard(jobId);
	}

	/** @Corefunctionscompleted */

	@PostMapping(path = "/updateJobOrder", consumes = "application/json", produces = "application/json")
	public ResponseEntity<JobOrder> updateJobOrder(@RequestBody JobOrder jobData) {
		JobOrder jo = jb.update(jobData);
		return ResponseEntity.ok(jo);
	}

	@PostMapping(path = "/getDashBoardData", consumes = "application/json", produces = "application/json")
	public List<JobOrderProjectionData> getDashboardData(@RequestBody JobOrderBucketData jobData) {
		String startDate = jobData.getStartDate();
		String endDate = jobData.getEndDate();
		Long bucketId = jobData.getBucketId();
		return jb.getDashboardData(startDate, endDate, bucketId);
	}

}
