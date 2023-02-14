package com.nexcode.model.dto;

import java.util.Set;

import com.nexcode.model.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private Long id;
	private String username;
	private String fullName;
	private String mobileNumber;
	private String password;
	private Set<Role>roles;
}
