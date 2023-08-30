package com.tcs.bindings;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Form {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer userID; 
	String userName;
	String company;
	String role;
	String email;
	String password;
	Integer salary;
	String address;
	String activeSW;
	String otp;

}
