package com.tcs.externalDto;

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

public class LoadAppId {
	
	Integer userId;
	String fullName;
	LocalDate DOB;
	String city;
	String state;
	String country;
	Integer ssNumber;

}
