package com.tcs.services;

import java.util.List;

import com.tcs.bindings.UpdatePasswordDto;
import com.tcs.model.SocialMediaUsers;

public interface SocialMediaUsersServices {
	
	SocialMediaUsers saveAllSmUsers(SocialMediaUsers socialMediaUsers);
	
	List<SocialMediaUsers> getAllSmUsers();
	
	SocialMediaUsers getSmUsersById(Integer Id);
	
	String updatePassword(UpdatePasswordDto updatePasswordDto);

}
