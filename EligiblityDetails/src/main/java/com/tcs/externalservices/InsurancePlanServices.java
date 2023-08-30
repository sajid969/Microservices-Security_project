package com.tcs.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tcs.externalDto.LoadPlanId;

@FeignClient(name="INSURANCEPLANAPPLICATION")
public interface InsurancePlanServices {
	
	@GetMapping("/insurance/plan/{Id}")
	public LoadPlanId getPlanId(@PathVariable Integer Id);

}
