package com.tcs.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.bindings.CreateAppDetailsDto;
import com.tcs.models.CreateAppDetails;
import com.tcs.service.CreateAppDetailsService;

@RestController
@RequestMapping("/createapp")
public class CreateAppDetailsController {
	
	@Autowired
	public CreateAppDetailsService createAppDetailsService;
	
	@PostMapping("/savedetails")
	public ResponseEntity<CreateAppDetailsDto> saveAllAppDetails(@RequestBody CreateAppDetailsDto createAppDetailsDto){
		CreateAppDetailsDto saveApplicationDetails = createAppDetailsService.saveApplicationDetails(createAppDetailsDto);
		return new ResponseEntity<CreateAppDetailsDto>(saveApplicationDetails,HttpStatus.CREATED);
	}
	
	@GetMapping("/getdetails/{appId}")
	public ResponseEntity<Optional<CreateAppDetails>> getAppDetails(@PathVariable Integer appId){
		Optional<CreateAppDetails> applicationDetails = createAppDetailsService.getApplicationDetails(appId);
		return new ResponseEntity<Optional<CreateAppDetails>>(applicationDetails,HttpStatus.OK);
	}

}
