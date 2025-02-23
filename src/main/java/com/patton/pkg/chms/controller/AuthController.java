package com.patton.pkg.chms.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.patton.pkg.chms.auth.JwtRequest;
import com.patton.pkg.chms.auth.JwtResponse;
import com.patton.pkg.chms.auth.JwtTokenUtil;
import com.patton.pkg.chms.auth.JwtUserDetailsServices;
import com.patton.pkg.chms.entity.UserTbl;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsServices usr;

	@GetMapping("/chms/help")
	public String helpProvider() {
		return "Help Provided...";
	}

	@GetMapping("/chms/getTokenInfo")
	public Date getUsername(HttpServletRequest request) {
		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		Date date = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				date = jwtTokenUtil.getExpirationDateFromToken(jwtToken);
			} catch (Exception e) {

			}
		}

		return date;
	}

	@Autowired
	private JwtUserDetailsServices userDetailsService;

	@RequestMapping(value = "/chms/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping(value = "/chms/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserTbl user) throws Exception {
		user.setUserStatus("Active");
		return ResponseEntity.ok(usr.registerUser(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
