package com.tcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.externalDto.LoadAppId;
import com.tcs.externalDto.LoadPlanId;
import com.tcs.models.EligibleDetails;
import com.tcs.services.EligibleDetailsService;
@RestController
@RequestMapping("/eligibility")
public class EligibleDetailsController {
	@Autowired
	public EligibleDetailsService eligibleDetailsService;
	
	@GetMapping("/casenumber/{casenumber}")
	public ResponseEntity<EligibleDetails> getEligible(@PathVariable Integer casenumber){
		return ResponseEntity.ok(eligibleDetailsService.saveEligibilityDetails(casenumber));
	}
	
	@GetMapping("/appid/{appId}")
	public ResponseEntity<LoadAppId> getAppDetails(@PathVariable Integer appId){
		return ResponseEntity.ok(eligibleDetailsService.getAppDetails(appId));
	}
	
	@GetMapping("/planid/{planId}")
	public ResponseEntity<LoadPlanId> getPlanDetails(@PathVariable Integer planId){
		return ResponseEntity.ok(eligibleDetailsService.getPlanDetails(planId));
	}
	
	
}
