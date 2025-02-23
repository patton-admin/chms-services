package com.patton.pkg.chms.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patton.pkg.chms.entity.UserTbl;
import com.patton.pkg.chms.entity.resultset.UserInf;
import com.patton.pkg.chms.entity.resultset.UserProjectionData;
import com.patton.pkg.chms.error.UserNotFoundException;
import com.patton.pkg.chms.repository.UserRepository;

@Service
public class UserServices {

	@Autowired
	UserRepository usr;

	@Autowired
	BucketServices bkt;

	/*** @CoreFuntions ***/

	/***
	 * @GetAllUsers to retrieve all User
	 **/
	public List<UserTbl> retrieveAllUsers() {
		return usr.findAll();
	}

	@Transactional
	/***
	 * @SaveUser to save User
	 **/
	public UserTbl save(UserTbl user) {
		return usr.save(user);
	}

	/**
	 * @DeleteUser delete bucket and its children's 1) get all bucket with owener id
	 *             2) call the deleteById In bucketService 3) delete the user...
	 **/
	@Transactional
	public void deleteUsers(List<Long> ids) {
		List<Long> users = usr.getAllBucketsByUserId(ids);
		bkt.deleteBuckets(users);
		usr.deleteUserByIdHard(ids);
	}

	/***
	 * @authentication
	 ***/
	public UserTbl getUserByEmail(String email) {
		UserTbl user = usr.findByUserEmail(email);
		return user;
	}

	/*** @CoreFunctions Completed ***/

	/** to get all User **/
	public Collection<UserProjectionData> getAllUsers() {
		return usr.findByNativeQuery();
	}

	/** to retrieve particular User by id **/
	public UserTbl findById(Long id) {
		return usr.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	public void deleteById(Long id) {
		usr.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		usr.deleteById(id);
	}

	// * to delete User soft delete
	@Transactional
	public int deleteUserById(Long id) {
		int deletedRowCount = usr.deleteUserById(id);
		return deletedRowCount;
	}

	// * to delete User soft delete
	@Transactional
	public int deleteUser(Long id) {
		int deletedRowCount = usr.deleteUser(id);
		return deletedRowCount;
	}

	/** to find the password for the user **/
	public Collection<UserInf> getPassword(Long id) {
		return usr.findUsersPassword(id);
	}

	public UserTbl getUserByFirstName(String firstName) {
		UserTbl user = usr.findByUserFirstName(firstName);
		user.getUserFirstName();
		return user;
	}

}
