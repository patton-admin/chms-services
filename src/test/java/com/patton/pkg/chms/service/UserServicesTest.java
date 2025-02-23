package com.patton.pkg.chms.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.patton.pkg.chms.entity.UserTbl;
import com.patton.pkg.chms.error.UserNotFoundException;

@SpringBootTest
public class UserServicesTest {

	public static long deleteId;

	@Autowired
	UserServices usr;

	@Test
	public void find_All_Users() {
		List<UserTbl> user = usr.retrieveAllUsers();
		assertThat(user).asList();
	}

	@Test
	public void findBy_Invalid_UserId() {
		Assertions.assertThrows(UserNotFoundException.class, () -> {
			usr.findById(402L);
		});
	}

	@Test
	public void findBy_Valid_UserId() {
		UserTbl user = usr.findById(30L);
		assertThat(user.getUserId()).isEqualTo(30L);
	}

	@Test
	public void save_UserId() {
		UserTbl user = new UserTbl();
		user.setUserPassword("Testing Save");
		user.setUserFirstName("testing");
		user.setUserLastName("testing");
		user.setUserPrimaryPhone("123456789011");
		user.setUserSecondaryPhone("123456789011");
		user.setUserRole("Test-Admin");
		user.setUserEmail("testing@gmail.com");
		user.setUserLocation("Testing");
		user.setResetQuestion1("what is your name?");
		user.setResetQuestion2("what is your name?");
		user.setResetans1("testing1");
		user.setResetans2("testing2");
		user.setCreatedBy("test");
		user.setUpdatedBy("test");
		UserTbl saved = usr.save(user);

		/*****
		 * check whether it is saved or not...
		 */
		assertThat(saved.getUserFirstName()).isEqualTo("testing");
	}

	@Test
	public void delete_user() {
		/***
		 * deleting the record...
		 */
		UserTbl user = usr.getUserByFirstName("testing");
		System.out.println("user..." + user.getUserEmail());
		System.out.println("user..." + user.getUserId());
		usr.deleteById(user.getUserId());

		/**
		 * validate whether record got deleted or not...
		 */
		Assertions.assertThrows(UserNotFoundException.class, () -> {
			usr.findById(user.getUserId());
		});
	}

}
