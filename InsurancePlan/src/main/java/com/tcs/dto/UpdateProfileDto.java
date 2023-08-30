package com.tcs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateProfileDto {
	
	String userName;
	String company;
	String role;
	String email;
	String password;
	Integer salary;
	String address;
	String activeSW;
	String confirmPwd;

}
