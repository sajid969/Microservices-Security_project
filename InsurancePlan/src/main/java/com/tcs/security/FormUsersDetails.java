package com.tcs.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tcs.models.Form;
import com.tcs.repositories.FormRepo;

public class FormUsersDetails implements UserDetailsService {
	
	@Autowired
	public FormRepo formRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Form> findByEmail = formRepo.findByEmail(username);
		
		return findByEmail.map(FormUsersRepoDetails::new).orElseThrow(()-> new UsernameNotFoundException("User not Found : "+username));
	}

}
