package com.tcs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SsnController {
	
	@GetMapping("/city/{ssn}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> getSsNumber(@PathVariable Integer ssn){
		String message="Not Newyork city";
		if(ssn>=60000000&&ssn<70000000) {
			message="Newyork city";
		}
		
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}

}
