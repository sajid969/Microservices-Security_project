package com.tcs.services;

import com.tcs.externalDto.LoadAppId;
import com.tcs.externalDto.LoadPlanId;
import com.tcs.models.EligibleDetails;

public interface EligibleDetailsService {
	
	EligibleDetails saveEligibilityDetails(Integer caseNumber);
	
	LoadAppId getAppDetails(Integer appId);
	
	LoadPlanId getPlanDetails(Integer planId);
	
	EligibleDetails createEligibilityDetails(Integer caseNumber);

}
