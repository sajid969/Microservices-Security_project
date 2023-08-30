package com.tcs.services;

import java.util.List;

import com.tcs.dto.ChangePasswordDto;
import com.tcs.dto.ConfirmPwdDto;
import com.tcs.dto.ForgotPasswordDto;
import com.tcs.dto.FormDto;
import com.tcs.dto.UpdateProfileDto;
import com.tcs.models.Form;

public interface FormServices {
	
	List<Form> getAllFormServices();
	
	String saveAllFormServices(Form form);
	
	String userLogin(Form form);
	
	List<Form> getByActiveSW(String active);
	
	String updatePassword(FormDto formDto);
	
	String requestOTP(ForgotPasswordDto forgotPasswordDto);
	
	String newPassword(ChangePasswordDto changePasswordDto);
	
	String deactivateAccount(String userName, String password,ConfirmPwdDto confirmPwdDto);
	
	String deleteAccount(String userName, String password,ConfirmPwdDto confirmPwdDto);
	
	String updateProfile(String userName, String password, UpdateProfileDto updateProfileDto);
	
	Form getAllUserIds(Integer Id);
	

}
