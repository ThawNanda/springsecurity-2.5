package com.nexcode.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexcode.model.request.UserLoginRequest;
import com.nexcode.model.response.ApiResponse;
import com.nexcode.model.response.JwtAuthenticationResponse;
import com.nexcode.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;

	private final JwtTokenProvider tokenProvider;

	@PostMapping("/user/login")
	public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody UserLoginRequest loginRequest) {
		Date expiredAt = new Date((new Date()).getTime() + 86400 * 1000);

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = tokenProvider.generateJwtToken(authentication);

			return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, expiredAt));
		}

		return new ResponseEntity<>(new ApiResponse(false, "Username or password incorrect."), HttpStatus.NOT_FOUND);
	}

}
