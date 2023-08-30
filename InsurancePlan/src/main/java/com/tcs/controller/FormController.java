package com.tcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.dto.ChangePasswordDto;
import com.tcs.dto.ConfirmPwdDto;
import com.tcs.dto.ForgotPasswordDto;
import com.tcs.dto.FormDto;
import com.tcs.dto.UpdateProfileDto;
import com.tcs.models.Form;
import com.tcs.security.FormUsersDetails;
import com.tcs.security.JwtUtils;
import com.tcs.services.FormServices;

@RestController
@RequestMapping("/insurance")
public class FormController {
	
	@Autowired
	public FormServices formServices;
	
	@Autowired
	public AuthenticationManager auth;
	@Autowired
	public FormUsersDetails formUsersDetails;
	@Autowired
	public JwtUtils jwtUtils;
	
	
	@GetMapping("/form")
	public ResponseEntity<List<Form>> getFormServices(){
		List<Form> allFormServices = formServices.getAllFormServices();
		return new ResponseEntity<List<Form>>(allFormServices, HttpStatus.OK);
	}
	
	@GetMapping("/user/{Id}")
	public ResponseEntity<Form> getUserIds(@PathVariable Integer Id){
		Form allUserIds = formServices.getAllUserIds(Id);
		return new ResponseEntity<Form>(allUserIds,HttpStatus.OK);
	}
	
	@PostMapping("/saveuser")
	public ResponseEntity<String> saveFormServices(@RequestBody Form form){
		String saveAllFormServices = formServices.saveAllFormServices(form);
		return new ResponseEntity<String>(saveAllFormServices,HttpStatus.ACCEPTED);
	}
	@PostMapping("/login")
	public ResponseEntity<String> userLogin(@RequestBody Form form) throws Exception{
		
		try {
			auth.authenticate(new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invalid Credentials");
		}
		UserDetails loadUserByUsername = formUsersDetails.loadUserByUsername(form.getEmail());
		String generateToken = jwtUtils.generateToken(loadUserByUsername.getUsername());
		
		
		//String saveAllFormServices = formServices.userLogin(form);
		return new ResponseEntity<String>(generateToken,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/form/{active}")
	public ResponseEntity<List<Form>> getActiveSW(@PathVariable String active){
		List<Form> byActiveSW = formServices.getByActiveSW(active);
		return new ResponseEntity<List<Form>>(byActiveSW,HttpStatus.OK);
		
	}
	
	@PostMapping("/updatepwd")
	public ResponseEntity<String> updatedPasswords(@RequestBody FormDto formDto){
		String updatePassword = formServices.updatePassword(formDto);
		return new ResponseEntity<String>(updatePassword,HttpStatus.ACCEPTED);
		
	}
	@PostMapping("/forgotpwd")
	public ResponseEntity<String> requestotp(@RequestBody ForgotPasswordDto forgotPasswordDto){
		String reqOTP = formServices.requestOTP(forgotPasswordDto);
		return new ResponseEntity<String>(reqOTP,HttpStatus.ACCEPTED);
		
	}
	@PostMapping("/forgotpwd/changepwd")
	public ResponseEntity<String> setNewPassword(@RequestBody ChangePasswordDto changePasswordDto){
		String newPassword = formServices.newPassword(changePasswordDto);
		return new ResponseEntity<String>(newPassword,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/deactive/{userName}/{password}")
	public ResponseEntity<String> tempDeactivateAccount(@PathVariable String userName,@PathVariable String password, @RequestBody ConfirmPwdDto confirmPwdDto){
		String deactivateAccount = formServices.deactivateAccount(userName, password, confirmPwdDto);
		return new ResponseEntity<String>(deactivateAccount,HttpStatus.ACCEPTED);	
	}
	
	@PostMapping("/delete/{userName}/{password}")
	public ResponseEntity<String> permanentDelete(@PathVariable String userName, @PathVariable String password, @RequestBody ConfirmPwdDto confirmPwdDto){
		String deleteAccount = formServices.deleteAccount(userName, password, confirmPwdDto);
		return new ResponseEntity<String>(deleteAccount,HttpStatus.ACCEPTED);	
	}
	
	@PatchMapping("/update/{userName}/{password}")
	public ResponseEntity<String> updateAccount(@PathVariable String userName, @PathVariable String password, @RequestBody UpdateProfileDto updateProfileDto){
		String updateAccount = formServices.updateProfile(userName, password, updateProfileDto);
		return new ResponseEntity<String>(updateAccount,HttpStatus.ACCEPTED);	
	}
	
	
	
	
	

}
