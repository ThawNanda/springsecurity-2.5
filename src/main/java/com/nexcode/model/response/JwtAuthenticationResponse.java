package com.nexcode.model.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse {

	private String token;

	private Date expiredAt;

	public JwtAuthenticationResponse(String token, Date expiredAt) {

		this.token = token;
		this.expiredAt = expiredAt;
	}

}
