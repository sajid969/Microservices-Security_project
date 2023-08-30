package com.tcs.bindings;

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

public class CreateAppResponse {
	
	Integer appId;
	Integer userId;
	String fullName;
	String city;
	String state;
	String country;
	Integer ssNumber;

}
