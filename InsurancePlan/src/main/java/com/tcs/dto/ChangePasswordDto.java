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

public class ChangePasswordDto {
	
	String userName;
	String otp;
	String newPwd;
	String confirmPwd;

}
