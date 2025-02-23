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

import com.patton.pkg.chms.entity.Bucket;
import com.patton.pkg.chms.entity.Client;
import com.patton.pkg.chms.service.BucketServices;
import com.patton.pkg.chms.service.ClientServices;

@RestController
@RequestMapping("/chms")
public class ClientController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ClientServices clnt;

	@Autowired
	BucketServices bkt;

	/** @CorefunctionsStart */

	@GetMapping("/allClients")
	public List<Client> getAllClients() {
		return clnt.retrieveAllClients();
	}

	@PostMapping(path = "/addClient", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Client> saveClient(@RequestBody Client clientData) {
		logger.info("Client -> {}", clientData.getBucketId());
		long bucketId = clientData.getBucketId();
		Bucket bucketClient = bkt.findById(bucketId);
		if (bucketClient == null) {
			return null;
		} else {

			Client newClient = new Client();

			newClient.setClientStatus("Active");
			newClient.setClientId(clientData.getClientId());
			newClient.setClientName(clientData.getClientName());
			newClient.setContactPerson(clientData.getContactPerson());
			newClient.setClientEmail(clientData.getClientEmail());
			newClient.setVendorName(clientData.getVendorName());
			newClient.setVendorEmail(clientData.getVendorEmail());
			newClient.setVendorContactPerson(clientData.getVendorContactPerson());

			bucketClient.addClient(newClient);
			newClient.setBcktClients(bucketClient);

			Client client = clnt.save(newClient);
			return ResponseEntity.ok(client);
		}
	}

	@PostMapping(path = "/deleteClients", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public void removeClientById(@RequestBody List<Long> clientId) {
		clnt.deleteById(clientId);
	}

	/** @Corefunctionscompleted */

	@PostMapping(path = "/updateClient", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Client> updateClient(@RequestBody Client clientData) {
		Client client = clnt.update(clientData);
		return ResponseEntity.ok(client);
	}

}
