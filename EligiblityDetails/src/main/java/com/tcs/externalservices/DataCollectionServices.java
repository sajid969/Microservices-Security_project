 package com.tcs.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tcs.externalDto.DcRequestDto;
import com.tcs.externalDto.LoadCaseNumber;
@FeignClient(name = "DATACOLLECTIONS")
public interface DataCollectionServices {
	@GetMapping("/datacollect/getappid/{id}")
	public LoadCaseNumber getByCaseNumber(@PathVariable Integer id);
	
	@GetMapping("/datacollect/preview/{caseNumber}")
	public DcRequestDto getDcDetails(@PathVariable("caseNumber") Integer Id);
	
}