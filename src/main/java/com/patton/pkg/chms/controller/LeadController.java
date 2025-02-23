package com.patton.pkg.chms.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.patton.pkg.chms.entity.Bucket;
import com.patton.pkg.chms.entity.JobOrderBucketData;
import com.patton.pkg.chms.entity.Lead;
import com.patton.pkg.chms.entity.resultset.LeadDashboardProjectionData;
import com.patton.pkg.chms.entity.resultset.LeadInf;
import com.patton.pkg.chms.service.BucketServices;
import com.patton.pkg.chms.service.LeadServices;

@RestController
@RequestMapping("/chms")
public class LeadController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LeadServices lead;

	@Autowired
	BucketServices bkt;

	/** @CorefunctionsStart */

	@GetMapping("/allLeads")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Lead> getAllLeads() {
		return lead.retrieveAllLeads();
	}

	@PostMapping(path = "/addLead", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Lead> saveLead(@RequestBody Lead leadData) {
		long bucketId = leadData.getBucketId();
		Bucket b = bkt.findById(bucketId);
		logger.info("value of bucket...", b.getId());
		if (b == null) {
			return null;
		} else {

			Lead newLead = new Lead(leadData.getId(), leadData.getLeadEmail(), leadData.getLeadPhone(),
					leadData.getLeadFirstName(), leadData.getLeadLastName(), leadData.getLeadPrimaryEmail(),
					leadData.getLeadSecondaryEmail(), leadData.getLeadAddress(), leadData.getLeadState(),
					leadData.getLeadCity(), leadData.getLeadEmpName(), leadData.getLeadJobTitle(),
					leadData.getLeadSalary(), leadData.getLeadVisaType(), leadData.getLeadWlgReLoc(),
					leadData.getLeadPrefLoc(), leadData.getLeadExpectedSalary(), leadData.getLeadPracticeArea(),
					leadData.getLeadStatus(), leadData.getLeadResFileName(), leadData.getLeadResPath(),
					leadData.getLeadNotes(), leadData.getComments(), leadData.getLeadJobOrderid(),
					leadData.getSubmittedJobId(), leadData.getTotalExp(), leadData.getCreatedDate(),
					leadData.getUpdatedDate());

			b.addLead(leadData);
			newLead.setBucket(b);

			Lead ld = lead.save(newLead);
			return ResponseEntity.ok(ld);
		}
	}

	@PostMapping(path = "/deleteByLeadId", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public void removeLeadById(@RequestBody List<Long> leadId) {
		lead.deleteLead(leadId);
	}

	/** @Corefunctionscompleted */

	@GetMapping("/getLead")
	@ResponseBody
	public Lead getLeadById(@RequestParam(required = true) Long id) {
		return lead.findById(id);
	}

	@GetMapping("/allLeadSubsetExampleUsingInterface")
	public Collection<LeadInf> getAllLeadsAndBucketid(@RequestParam(required = true) Long id) {
		return lead.leadAndBucketSubset(id);
	}

	@PostMapping(path = "/getDashBoardLeadData", consumes = "application/json", produces = "application/json")
	public List<LeadDashboardProjectionData> getDashboardData(@RequestBody JobOrderBucketData jobData) {
		String startDate = jobData.getStartDate();
		String endDate = jobData.getEndDate();
		Long bucketId = jobData.getBucketId();
		return lead.dashboardLeadData(startDate, endDate, bucketId);
	}

}
