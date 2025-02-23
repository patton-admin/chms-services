package com.patton.pkg.chms.service;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patton.pkg.chms.entity.Lead;
import com.patton.pkg.chms.entity.resultset.LeadDashboardProjectionData;
import com.patton.pkg.chms.entity.resultset.LeadInf;
import com.patton.pkg.chms.error.LeadNotFoundException;
import com.patton.pkg.chms.repository.LeadRepository;

@Service
public class LeadServices {

	@Autowired
	LeadRepository ld;

	@Autowired
	EntityManager em;

	/** to retrieve all Lead **/
	public List<Lead> retrieveAllLeads() {
		return ld.findAll();
	}

	/** to retrieve particular Lead by id **/
	public Lead findById(Long id) {
		Lead lead = ld.findById(id).orElseThrow(() -> new LeadNotFoundException(id));
		return lead;
	}

	/** to retrieve particular Lead by bucketid **/
	public Lead findByBucketId(Long id) {
		Lead Lead = ld.findById(id).orElse(null);
		return Lead;
	}

	/** to save Lead **/
	public Lead save(Lead lead) {
		Lead l = ld.save(lead);
		return l;
	}

	/** to delete Lead - soft Delete Example **/
	@Transactional
	public int deleteLeadById(Long id) {
		int deletedRowCount = ld.deleteLeadById(id);
		return deletedRowCount;
	}

	// hard delete
	public void deleteLead(List<Long> leadId) {
		ld.deleteLeadByIdHard(leadId);
	}

	/**
	 * to get Lead and bucket
	 **/
	public Collection<LeadInf> leadAndBucketSubset(Long id) {
		return ld.findLeadById(id);
	}

	public Lead findLeadByEmail(String email) {
		Lead lead = ld.findByLeadPrimaryEmail(email);
		return lead;
	}

	public List<LeadDashboardProjectionData> dashboardLeadData(String startDate, String endDate, Long bucketId) {
		if (bucketId > 0) {
			List<LeadDashboardProjectionData> leads = ld.filterleadByDateAndBucketId(startDate, endDate, bucketId);
			return leads;
		} else {
			List<LeadDashboardProjectionData> leads = ld.filterLeadOnlyByDate(startDate, endDate);
			return leads;
		}

	}

	/**
	 * typed Query Example...
	 **/
//	public TypedQuery<Lead> findOnlyLeadByTypedQuery(Long id) {
//		TypedQuery<Lead> q = em.createQuery("SELECT p " + "FROM Lead p WHERE p.id = :format", Lead.class);
//		q.setParameter("format", id);
//		return q;
//	}

}
