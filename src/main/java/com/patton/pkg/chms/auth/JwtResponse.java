package com.patton.pkg.chms.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

	private static final long serialVersionUID = -8091879091924046844L;
	private String jwtToken;
}
