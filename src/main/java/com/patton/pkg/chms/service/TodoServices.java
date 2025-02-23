package com.patton.pkg.chms.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patton.pkg.chms.entity.Todo;
import com.patton.pkg.chms.repository.TodoRepository;

@Service
public class TodoServices {

	@Autowired
	TodoRepository ld;

	@Autowired
	EntityManager em;

	/** to retrieve all Todo **/
	public List<Todo> retrieveAllTodos() {
		return ld.findAll();
	}

	public Todo saveTodo(Todo todo) {
		return ld.save(todo);
	}

	@Transactional
	/** to delete Client **/
	public void deleteById(List<Long> ids) {
		ld.deleteTodo(ids);
	}

}
