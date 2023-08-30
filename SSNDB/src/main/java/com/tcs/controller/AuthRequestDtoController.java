package com.tcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.bindings.AuthRequestDto;
import com.tcs.security.JwtUtils;
import com.tcs.security.MySmUsersDetails;

@RestController
public class AuthRequestDtoController {
	
	@Autowired
	public JwtUtils jwtUtils;

	@Autowired
	public AuthenticationManager auth;
	
	@Autowired
	public MySmUsersDetails mySmUsersDetails;
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> smLoginDetails(@RequestBody AuthRequestDto authRequestDto) throws Exception{
		
		try {
			auth.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUserName(), authRequestDto.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invalid Credentials");
		}
		
		UserDetails smUserDetails = mySmUsersDetails.loadUserByUsername(authRequestDto.getUserName());
		
		String generateToken = jwtUtils.generateToken(smUserDetails.getUsername());
		return new ResponseEntity<String>(generateToken,HttpStatus.OK);
		
	}

}
