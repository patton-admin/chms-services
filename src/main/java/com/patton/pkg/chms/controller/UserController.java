
package com.patton.pkg.chms.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.patton.pkg.chms.auth.JwtTokenUtil;
import com.patton.pkg.chms.entity.UserTbl;
import com.patton.pkg.chms.entity.resultset.UserInf;
import com.patton.pkg.chms.entity.resultset.UserProjectionData;
import com.patton.pkg.chms.service.UserServices;

@RestController
@RequestMapping("/chms")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserServices usr;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	/** @CorefunctionsStart */

	@GetMapping("/allUsers")
	public List<UserTbl> getAllUsers(HttpServletRequest request) {
		return usr.retrieveAllUsers();
	}

	@PostMapping(path = "/addUser", consumes = "application/json", produces = "application/json")
	public UserTbl addUser(@RequestBody UserTbl user) {
		if (user.getUserId() == null) {
			user.setUserStatus("Active");
			user.setUserPassword(bcryptEncoder.encode(user.getUserPassword()));
		} else {
			if (user.getUserPassword().length() > 5 && user.getUserPassword().length() < 50) {
				user.setUserPassword(bcryptEncoder.encode(user.getUserPassword()));
			}
		}

		UserTbl u = usr.save(user);
		return u;
	}

	@PostMapping(path = "/deleteByUserId", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public void removeUsrById(@RequestBody List<Long> usrIds) {
		usr.deleteUsers(usrIds);
		;
	}

	/** @Corefunctionscompleted */

	@GetMapping("/getPassword")
	public Collection<UserInf> getPassword(@RequestParam(required = true) Long id) {
		return usr.getPassword(id);
	}

	@PostMapping(path = "/updateUserPassword", consumes = "application/json", produces = "application/json")
	public int updatePassword(@RequestParam(required = true) Long id) {
		return 0;
	}

	@GetMapping("/removeUser")
	public int removeUser(@RequestParam(required = true) Long userId) {
		return usr.deleteUser(userId);
	}

	@GetMapping("/getAllUsers")
	public Collection<UserProjectionData> getAllUser() {
		return usr.getAllUsers();
	}

}
