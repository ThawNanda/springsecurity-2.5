package com.nexcode.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {

	private String username;
	private String password;
}
