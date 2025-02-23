package com.patton.pkg.chms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

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

import com.patton.pkg.chms.entity.MailUser;
import com.patton.pkg.chms.entity.Todo;
import com.patton.pkg.chms.service.MailService;
import com.patton.pkg.chms.service.TodoServices;

@RestController
@RequestMapping("/chms")
public class TodoController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MailService mailer;

	@Autowired
	TodoServices td;

	@Autowired
	MailUser user;

	/** @CorefunctionsStart */

	@GetMapping("/allTodo")
	public List<Todo> getAlltodos() {
		return td.retrieveAllTodos();
	}

	@PostMapping(path = "/addTodo", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Todo> savetodo(@RequestBody Todo todoData) {
		if (todoData.getId() > 0) {
			Todo todo = td.saveTodo(todoData);
			return ResponseEntity.ok(todo);
		} else {
			Todo todo = td.saveTodo(todoData);
			return ResponseEntity.ok(todo);
		}
	}

	@PostMapping(path = "/delete", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public void removeTodoById(@RequestBody List<Long> todoId) {
		td.deleteById(todoId);
	}

	/** @Corefunctionscompleted */

//	@Scheduled(cron = "0 16 * * * ?")
	public void sendEmail() throws MessagingException, ParseException {

		logger.info("Email to be send...");
		// getting today's Date...
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String strDate = sdf.format(date);

		// get all the records from the db...x`
		List<Todo> todoList = td.retrieveAllTodos();

		todoList.stream().forEach(c -> {

			// d1 === d2 -> 0
			// d1 is after d2 -> 1
			// d1 is before d2 -> < 0
			try {
				if (c.getStatus().equals("completed") || c.getStatus().equals("reviewed")
						|| c.getStatus().equals("closed")) {

				} else {

					if (strDate.compareTo(c.getEndDate()) == 0) {
						logger.info("Task is due today...", c.getUserEmail());
						user.setEmailAddress(c.getUserEmail());
						user.setSub("Task is Due Today..." + c.getShortDescription());
						user.setBody(c.getLongDescription());
						mailer.sendEmail(user);
					} else if (strDate.compareTo(c.getEndDate()) > 0) {
						logger.info("compare...", strDate.compareTo(c.getEndDate()));
						logger.info("Task is over due... End Date...", c.getEndDate(), "todays date...", strDate);
						logger.info("Task is over due...", c.getUserEmail());
						user.setEmailAddress(c.getUserEmail());
						user.setSub("Task is Over Due..." + c.getShortDescription());
						user.setBody(c.getLongDescription());
						mailer.sendEmail(user);
					} else if (strDate.compareTo(c.getEndDate()) < 0) {
						logger.info("Task is not yet due...", c.getUserEmail());
					}
				}
			} catch (Exception e) {
				logger.info("Chms-service Exception");
				user.setEmailAddress("aravind111991@gmail.com");
				user.setSub("Chms-service Exception..." + c.getShortDescription());
				user.setBody(e.getLocalizedMessage());
				mailer.sendEmail(user);
			}

		});

	}

}
