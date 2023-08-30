package com.tcs.dto;

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

public class FormDto {
	
	String userName;
	String oldPwd;
	String newPwd;
	String confirmPwd;

}
