package com.tcs.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcs.dto.ChangePasswordDto;
import com.tcs.dto.ConfirmPwdDto;
import com.tcs.dto.ForgotPasswordDto;
import com.tcs.dto.FormDto;
import com.tcs.dto.UpdateProfileDto;
import com.tcs.exceptionalhandling.ResourceNotFound;
import com.tcs.models.Form;
import com.tcs.repositories.FormRepo;
import com.tcs.security.JwtUtils;
import com.tcs.utils.EmailSender;

@Service
public class FormServicesImpl implements FormServices {
	private final Logger logger = LoggerFactory.getLogger(FormServices.class);

	@Autowired
	public FormRepo formRepo;
	
	@Autowired
	public EmailSender emailSender;
	@Autowired
	public PasswordEncoder passwordEncoder;
	@Autowired
	public JwtUtils jwtUtils;
	
	@Override
	public List<Form> getAllFormServices() {
		List<Form> findAll = formRepo.findAll();
		return findAll;
	}

	@Override
	public String saveAllFormServices(Form form) {
		String response = "Saved Successfully";
		String url="http://localhost:9090/updatepwd";
		String email = form.getEmail();
		String userName = form.getUserName();
		Optional<Form> findByEmail = formRepo.findByEmail(email);
		if(findByEmail.isPresent()) {
			response = "Email Already Exists...!!";
			return response;
		}
		Optional<Form> findByUserName = formRepo.findByUserName(userName);
		if(findByUserName.isPresent()) {
			response="UserName is already taken by someone so please try another";
			return response;
		}
		String password = generatePassword(8);
		form.setPassword(passwordEncoder.encode(password));
		form.setActiveSW("deactive");
		Form save = formRepo.save(form);
		String fileName = "EmailBody.txt";
		String sendEmailBody = sendEmailBody(fileName,save.getUserName(), password, url);
		emailSender.sendEmail(form.getEmail(), "REGISTRATION IS COMPLETED",sendEmailBody );
		return response;
	}
	
	
	
	
	 public static String generatePassword(int length) {
	      String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
	      String numbers = "1234567890";
	      String combinedChars = capitalCaseLetters + lowerCaseLetters + numbers;
	      Random random = new Random();
	      char[] password = new char[length];
	      //String password = "";
	      password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
	      password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
	      password[2] = numbers.charAt(random.nextInt(numbers.length()));
	   
	      for(int i = 3; i< length ; i++) {
	         password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
	      }
	      return new String(password);
	   }

	@Override
	public String userLogin(Form form) {
		logger.info("user login service started...!!");
		String response = "Invalid username";
		String userName = form.getUserName();
		String password = form.getPassword();
		Optional<Form> findByUserName = formRepo.findByUserName(userName);
		Optional<Form> findByUserNameAndPassword = formRepo.findByUserNameAndPassword(userName, passwordEncoder.encode(password));
		if(findByUserName.isPresent()) {
			if(findByUserNameAndPassword.isPresent()) {
				findByUserNameAndPassword.get().setActiveSW("active");
				formRepo.save(findByUserNameAndPassword.get());
				response = "login successfull";
				return response;
			}
			response = "Invalid password";
		}
		logger.info("user login service Stopped...!!");
		return response;
		
	}
	
	

	@Override
	public List<Form> getByActiveSW(String active) {
		List<Form> findByActiveSW = formRepo.findByActiveSW(active);
		return findByActiveSW;
	}

	@Override
	public String updatePassword(FormDto formDto) {
		String message="given credentials are invalid";
		Optional<Form> findByUserNameAndPassword = formRepo.findByUserNameAndPassword(formDto.getUserName() ,formDto.getOldPwd());
		if(findByUserNameAndPassword.isPresent()) {
			message="old password and new password are not same";
			if(formDto.getNewPwd().equals(formDto.getConfirmPwd())) {
				findByUserNameAndPassword.get().setPassword(passwordEncoder.encode(formDto.getNewPwd() ));
				findByUserNameAndPassword.get().setActiveSW("active");
				formRepo.save(findByUserNameAndPassword.get());
				message="password updated succefully";
			}
			
		}
		return message;
	}

	@Override
	public String requestOTP(ForgotPasswordDto forgotPasswordDto) {
		String email = forgotPasswordDto.getEmail();
		Optional<Form> findByEmail = formRepo.findByEmail(email);
		String message = "Given Mail ID is not found";
		if(findByEmail.isPresent()) {
			String otp = generateOTP(6);
			findByEmail.get().setOtp(otp);
			formRepo.save(findByEmail.get());
			String url = "";
			String fileName = "ForgotPwd.txt";
			String sendEmailBody = sendEmailBody(fileName,findByEmail.get().getUserName(), findByEmail.get().getOtp(), url);
			emailSender.sendEmail(findByEmail.get().getEmail(), "REQUESTED OTP",sendEmailBody );
			message = "OTP sent to mail, please check it";
			
			try {
	            Thread.sleep(1 * 60 * 1000); // Wait for 1 minutes
	            findByEmail.get().setOtp(null);
	            formRepo.save(findByEmail.get());
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		}
		return message;
	}
	public static String generateOTP(int length) {  
	    String numbers = "0123456789";  
	    Random rndm_method = new Random();  
	    char[] otp = new char[length];  
	    for (int i = 0; i < length; i++) {  
	        otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));  
	    }  
	    return new String(otp);  
	}
	public String sendEmailBody(String fileName, String userName, String password, String url) {
		String mailBody=null;
		try {
			FileReader fr= new FileReader(fileName);
			BufferedReader br=new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();
			String readLine = br.readLine();
			
			while(readLine!=null) {
				sb.append(readLine);
				readLine=br.readLine();
			}
			br.close();
			mailBody=sb.toString();
			mailBody = mailBody.replace("{USERNAME}", userName);
			mailBody = mailBody.replace("{PWD}", password);
			mailBody = mailBody.replace("{OTP}", password);
			mailBody = mailBody.replace("{URL}", url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mailBody;
	}

	@Override
	public String newPassword(ChangePasswordDto changePasswordDto) {
		Optional<Form> findByUserNameAndOtp = formRepo.findByUserNameAndOtp(changePasswordDto.getUserName(),changePasswordDto.getOtp());
		String message="otp is invalid";
		if(findByUserNameAndOtp.isPresent()) {
			message="new password and confirm password are not same";
			if(changePasswordDto.getNewPwd().equals(changePasswordDto.getConfirmPwd())) {
				findByUserNameAndOtp.get().setPassword(changePasswordDto.getNewPwd());
				findByUserNameAndOtp.get().setActiveSW("active");
				findByUserNameAndOtp.get().setOtp(null);
				formRepo.save(findByUserNameAndOtp.get());
				message="saved succesfully";
			}
		}
		return message;
	}

	@Override
	public String deactivateAccount(String userName, String password,ConfirmPwdDto confirmPwdDto) {
		Optional<Form> findByUserNameAndPassword = formRepo.findByUserNameAndPassword(userName, password);
		String message = "please check the credentials";
		if(findByUserNameAndPassword.isPresent()) {
			String password2 = findByUserNameAndPassword.get().getPassword();
			String confirmpwd = confirmPwdDto.getConfirmpwd();
			message = "wrong password";
			if(password2.equals(confirmpwd)) {
				findByUserNameAndPassword.get().setActiveSW("deactive");
				formRepo.save(findByUserNameAndPassword.get());
				message = "account deactivated";
			}
		}
		
		return message;
	}

	@Override
	public String deleteAccount(String userName, String password, ConfirmPwdDto confirmPwdDto) {
		Form findByUserNameAndPassword = formRepo.findByUserNameAndPassword(userName, password).orElseThrow(()->new ResourceNotFound("Credentails are incorrect "));
			String password2 = findByUserNameAndPassword.getPassword();
			String confirmpwd = confirmPwdDto.getConfirmpwd();
			String message = "wrong password";
			if(password2.equals(confirmpwd)) {
				formRepo.delete(findByUserNameAndPassword);
				message = "account deleted";
		}
		return message;
	}

	@Override
	public String updateProfile(String userName, String password, UpdateProfileDto updateProfileDto) {
		Optional<Form> findByUserNameAndPassword = formRepo.findByUserNameAndPassword(userName, password);
		String message = "please check the credentials";
		if(findByUserNameAndPassword.isPresent()) {
			String password2 = findByUserNameAndPassword.get().getPassword();
			String confirmpwd = updateProfileDto.getConfirmPwd();
			String userName2 = updateProfileDto.getUserName();
			String email = updateProfileDto.getEmail();
			Optional<Form> findByUserName = formRepo.findByUserName(userName2);
			if(userName2!=null) {
				if(findByUserName.isEmpty()) {
					findByUserNameAndPassword.get().setUserName(userName2);
					message = "please confirm the password";
				}else {
					message = "User name is already taken, please try with another user name";
					return message;
				}
			}
			
			if(email!=null) {
				Optional<Form> findByEmail = formRepo.findByEmail(email);
				if(findByEmail.isEmpty()) {
					findByUserNameAndPassword.get().setEmail(email);
					message = "please confirm the password";
				}else{
					message = "Email is already exists, please try with another email";
					return message;
				}
			}
			
			if(updateProfileDto.getAddress()!=null) {
				findByUserNameAndPassword.get().setAddress(updateProfileDto.getAddress());
			}
			if(updateProfileDto.getRole()!=null) {
				findByUserNameAndPassword.get().setRole(updateProfileDto.getRole());
			}
			if(updateProfileDto.getSalary()!=null) {
				findByUserNameAndPassword.get().setSalary(updateProfileDto.getSalary());
			}
			if(password2.equals(confirmpwd)) {
				formRepo.save(findByUserNameAndPassword.get());
				message="updated succesfully";
			}else if(confirmpwd!=null){
				message = "wrong password";
			}
		}
		return message;
	}

	@Override
	public Form getAllUserIds(Integer Id) {
		return formRepo.findById(Id).orElseThrow(()->new ResourceNotFound("User Id Not Found : "+Id));
		
		
	}
	
	

	
	 

}
