package com.tcs.bindings;

import java.time.LocalDate;

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
public class CreateAppDetailsDto {
	
	String message;
	Integer appId;
	String fullName;
	LocalDate DOB;
	Integer userId;
	String city;
	String state;
	String country;
	Integer ssNumber;

}
