package com.tcs.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tcs.model.SocialMediaUsers;
import com.tcs.repositaries.SocialMediaUsersRepo;

public class MySmUsersDetails implements UserDetailsService {
	
	@Autowired
	public SocialMediaUsersRepo socialMediaUsersRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<SocialMediaUsers> findByUserName = socialMediaUsersRepo.findByUserName(username);
		
		return findByUserName.map(SmUsersRepoDetails::new).orElseThrow(()-> new UsernameNotFoundException("User not Found : "+username));
	}

}
