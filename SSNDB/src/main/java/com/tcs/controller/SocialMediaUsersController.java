package com.tcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.bindings.UpdatePasswordDto;
import com.tcs.model.SocialMediaUsers;
import com.tcs.services.SocialMediaUsersServices;

@RestController
@RequestMapping("/socialmediausers")
public class SocialMediaUsersController {
	
	@Autowired
	public SocialMediaUsersServices socialMediaUsersServices;
	
	@PostMapping("/saveuser")
	public ResponseEntity<SocialMediaUsers> saveSmUsers(@RequestBody SocialMediaUsers socialMediaUsers){
		SocialMediaUsers saveAllSmUsers = socialMediaUsersServices.saveAllSmUsers(socialMediaUsers);
		return new ResponseEntity<SocialMediaUsers>(saveAllSmUsers,HttpStatus.ACCEPTED);		
	}
	
	@GetMapping("/getallusers")
	public ResponseEntity<List<SocialMediaUsers>> getSmUsers(){
		List<SocialMediaUsers> allSmUsers = socialMediaUsersServices.getAllSmUsers();
		return new ResponseEntity<List<SocialMediaUsers>>(allSmUsers,HttpStatus.OK);
	}
	
	@GetMapping("/getuser/{Id}")
	public ResponseEntity<SocialMediaUsers> getOnlySmUser(@PathVariable Integer Id){
		SocialMediaUsers smUsersById = socialMediaUsersServices.getSmUsersById(Id);
		return new ResponseEntity<SocialMediaUsers>(smUsersById,HttpStatus.OK);
	}
	
	@PostMapping("/updatepassword")
	public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordDto updatePasswordDto){
		String updatePassword = socialMediaUsersServices.updatePassword(updatePasswordDto);
		return new ResponseEntity<String>(updatePassword,HttpStatus.ACCEPTED);
	}

}
