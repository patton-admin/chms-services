package com.patton.pkg.chms.auth;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.patton.pkg.chms.entity.UserTbl;
import com.patton.pkg.chms.service.UserServices;

@Service
public class JwtUserDetailsServices implements UserDetailsService {

	@Autowired
	private UserServices usr;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserTbl user = usr.getUserByEmail(username);

		if (user != null) {
			return new User(user.getUserEmail(), user.getUserPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	public UserTbl registerUser(UserTbl userTbl) {
		UserTbl newUser = new UserTbl();
		newUser.setUserLoginId(userTbl.getUserLoginId());
		newUser.setUserPassword(bcryptEncoder.encode(userTbl.getUserPassword()));
		newUser.setUserFirstName(userTbl.getUserFirstName());
		newUser.setUserLastName(userTbl.getUserLastName());
		newUser.setUserPrimaryPhone(userTbl.getUserPrimaryPhone());
		newUser.setUserSecondaryPhone(userTbl.getUserSecondaryPhone());
		newUser.setUserRole(userTbl.getUserRole());
		newUser.setUserEmail(userTbl.getUserEmail());
		newUser.setUserLocation(userTbl.getUserLocation());
		newUser.setResetQuestion1(userTbl.getResetQuestion1());
		newUser.setResetQuestion2(userTbl.getResetQuestion2());
		newUser.setResetans1(userTbl.getResetans1());
		newUser.setResetans2(userTbl.getResetans2());

		newUser.setCreatedBy(userTbl.getCreatedBy());
		newUser.setUpdatedBy(userTbl.getUpdatedBy());

		UserTbl createdUser = usr.save(newUser);
		return createdUser;

	}

}
