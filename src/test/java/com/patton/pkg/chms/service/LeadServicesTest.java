package com.patton.pkg.chms.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.patton.pkg.chms.entity.Bucket;
import com.patton.pkg.chms.entity.Lead;
import com.patton.pkg.chms.error.LeadNotFoundException;

@SpringBootTest
public class LeadServicesTest {

	long leadId = 0L;

	@Autowired
	LeadServices ld;

	@Autowired
	BucketServices bkt;

	@Test
	public void find_All_Leads() {
		List<Lead> Lead = ld.retrieveAllLeads();
		assertThat(Lead).asList();
	}

	@Test
	public void findBy_Invalid_LeadId() {
		Assertions.assertThrows(LeadNotFoundException.class, () -> {
			ld.findById(402L);
		});
	}

	@Test
	public void findBy_Valid_LeadId() {
		Lead user = ld.findById(39L);
		assertThat(user.getId()).isEqualTo(39L);
	}

	@Test
	// the execution of the code is running on the based on the alphabetical order
	public void asave_Lead() {
		Lead newLead = new Lead();
		newLead.setLeadEmail("testPolicy@gmail.com");
		newLead.setLeadPrimaryEmail("testPolicy@gmail.com");
		newLead.setLeadPhone("1211");
		newLead.setLeadFirstName("testPolicy");
		newLead.setLeadLastName("Ney");
		newLead.setLeadState("Mal");
		newLead.setLeadEmpName("Dnk");
		newLead.setLeadJobTitle("sr sft");
		newLead.setLeadSalary(12100);
		newLead.setLeadVisaType("h1");
		newLead.setLeadExpectedSalary(12100);
		newLead.setLeadPracticeArea("js");
		newLead.setLeadStatus("active");
		newLead.setLeadResFileName("testPolicy");

		// step 1:
		Bucket b = bkt.findById(1L);
		// b.addLead(newLead);

		// step 2:
		newLead.setBucket(b);
		Lead lead = ld.save(newLead);
		leadId = lead.getId();
		System.out.println("LeadName........................." + leadId);

		/*****
		 * check whether it is saved or not...
		 */
		assertThat(lead.getLeadPrimaryEmail()).isEqualTo("testPolicy@gmail.com");
	}

	/**
	 * deleting the record...
	 */
	@Test
	public void delete_Lead() {

		Lead lead = ld.findLeadByEmail("testPolicy@gmail.com");
		/**
		 * deletion
		 */
		System.out.println("LeadName..." + leadId);
		long leadId = lead.getId();
//		ld.deleteLead(leadId);
		/**
		 * validate whether record got deleted...
		 ***/
		Assertions.assertThrows(LeadNotFoundException.class, () -> {
			ld.findById(leadId);
		});

	}

}
