package com.tcs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance")
public class SSNNumber {
	
	@GetMapping("/ssn/{number}")
	public ResponseEntity<String> getCity(@PathVariable Long number){
		String message = "Not a NewYork City";
		if(number >= 600000000 && number <=699999999) {
			message = "NewYork City";
		}
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
