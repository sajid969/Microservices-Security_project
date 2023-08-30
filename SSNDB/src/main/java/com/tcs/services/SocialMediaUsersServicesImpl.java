package com.tcs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcs.bindings.UpdatePasswordDto;
import com.tcs.model.SocialMediaUsers;
import com.tcs.repositaries.SocialMediaUsersRepo;
import com.tcs.security.JwtRequestFilter;
import com.tcs.security.JwtUtils;

@Service
public class SocialMediaUsersServicesImpl implements SocialMediaUsersServices {
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	public SocialMediaUsersRepo socialMediaUsersRepo;

	@Override
	public SocialMediaUsers saveAllSmUsers(SocialMediaUsers socialMediaUsers) {
		String encode = passwordEncoder.encode(socialMediaUsers.getPassword());
		socialMediaUsers.setPassword(encode);
		SocialMediaUsers save = socialMediaUsersRepo.save(socialMediaUsers);
		return save;
	}

	@Override
	public List<SocialMediaUsers> getAllSmUsers() {
		List<SocialMediaUsers> findAll = socialMediaUsersRepo.findAll();
		return findAll;
	}

	@Override
	public SocialMediaUsers getSmUsersById(Integer Id) {
		SocialMediaUsers smUser = socialMediaUsersRepo.findById(Id).orElseThrow(null);
		return smUser;
	}

	@Override
	public String updatePassword(UpdatePasswordDto updatePasswordDto) {
		String message="given credentials are invalid";
		String username = JwtRequestFilter.defaultUserName;
		Optional<SocialMediaUsers> findByUserNameAndPassword = socialMediaUsersRepo.findByUserNameAndPassword(username, updatePasswordDto.getOldPassword());
		if(findByUserNameAndPassword.isPresent()) {
			message="old password and new password are not same";
			if(updatePasswordDto.getNewPassword().equals(updatePasswordDto.getConfirmPassword())) {
				findByUserNameAndPassword.get().setPassword(updatePasswordDto.getNewPassword());
				socialMediaUsersRepo.save(findByUserNameAndPassword.get());
				message="password updated succefully";
			}

		}
		
		return message;
	}

}
