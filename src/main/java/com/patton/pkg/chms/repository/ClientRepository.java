package com.patton.pkg.chms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.patton.pkg.chms.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	@Transactional
	@Modifying
	@Query("delete from Client u WHERE u.id = ?1")
	void deleteByClientId(String clientId);

	// softDelete the Operation in jpa...
	@Query("UPDATE Client e SET e.clientStatus='InActive' WHERE e.id =?1")
	@Modifying
	public int deleteClient(String id);

	@Query("DELETE FROM Client e WHERE e.clientId IN (?1)")
	@Modifying
	void deleteClients(List<Long> clientId);
}
