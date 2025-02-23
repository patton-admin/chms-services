package com.patton.pkg.chms.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patton.pkg.chms.entity.Bucket;
import com.patton.pkg.chms.entity.Client;
import com.patton.pkg.chms.error.ClientNotFoundException;
import com.patton.pkg.chms.repository.ClientRepository;

@Service
public class ClientServices {

	@Autowired
	ClientRepository client;

	@Autowired
	JobOrderServices jo;

	@Autowired
	EntityManager em;

	/** to retrieve all Client **/
	public List<Client> retrieveAllClients() {
		return client.findAll();
	}

	/** to retrieve particular Client by id **/
	public Client findById(Long id) {
		Client cl = client.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
		return cl;
	}

	/** to save Client **/
	public Client save(Client clien) {
		Client cl = client.save(clien);
		return cl;
	}

	@Transactional
	/** to delete Client **/
	public void deleteById(List<Long> ids) {
		List<Long> joborders = jo.getAllJobOrders(ids);
		jo.deleteJobOrderByHard(joborders);
		client.deleteClients(ids);
	}

	/**
	 * Soft Delete
	 * 
	 * @param clientName
	 */
	@Transactional
	public int deleteById(String clientName) {
		int cl = client.deleteClient(clientName);
		return cl;
	}

	/**
	 * update the client...
	 */
	public Client update(Client clientData) {
		long clientId = clientData.getClientId();
		Bucket bucketId = clientData.getBcktClients();
		return null;
	}

}
