package com.tcs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.models.Form;


public interface FormRepo extends JpaRepository<Form, Integer>  {
	
	Optional<Form> findByEmail(String email);
	
	Optional<Form> findByUserName(String userName);
	
	Optional<Form> findByUserNameAndPassword(String userName, String password);
	
	List<Form> findByActiveSW(String active);
	
	Optional<Form> findByUserNameAndOtp(String userName, String otp);
	
}
