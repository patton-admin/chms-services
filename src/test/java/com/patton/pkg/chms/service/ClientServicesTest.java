package com.patton.pkg.chms.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.patton.pkg.chms.entity.Bucket;
import com.patton.pkg.chms.entity.Client;

@SpringBootTest
public class ClientServicesTest {

	@Autowired
	ClientServices clnt;

	@Test
	public void find_All_Clients() {
		List<Client> client = clnt.retrieveAllClients();
		assertThat(client).asList();
	}

//	@Test
//	public void findBy_Valid_ClientId() {
//		Client user = clnt.find("bind");
//		assertThat(user.getClientName()).isEqualTo("aetna");
//	}

	@Test
	public void save_client() {
		Client client = new Client();
		client.setClientName("testingClientNm");
		client.setContactPerson("testingContactPerson");
		client.setClientEmail("testingEmail");
		client.setVendorName("testingVendorName");
		client.setVendorEmail("testingVendorEmail");
		client.setVendorContactPerson("vendorContactPerson");

		// creating a dummy bucket and saving the dummy client to that bucket...
		Bucket bkt = new Bucket();
		bkt.setId(2L);

		client.setBcktClients(bkt);

		Client saved = clnt.save(client);

		/*****
		 * check whether it is saved or not...
		 */
		assertThat(saved.getClientEmail()).isEqualTo("testingEmail");
	}

	@Test
	public void delete_user() {
		/***
		 * deleting the record...
		 */
//		Client client = clnt.findByClient_Id("testingClientId");
//		System.out.println("clientName..." + client.getClientId());
//		clnt.deleteById(client.getClientId());

		/**
		 * validate whether record got deleted or not...
		 */
	}

}
