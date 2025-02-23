package com.patton.pkg.chms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.patton.pkg.chms.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

	@Query("DELETE FROM Todo e WHERE e.id IN (?1)")
	@Modifying
	void deleteTodo(List<Long> clientId);
}
