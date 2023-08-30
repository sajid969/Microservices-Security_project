package com.tcs.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tcs.externalDto.LoadAppId;

@FeignClient(name="CREATEAPPLICATION")
public interface CreateApplicationServices {
	
	@GetMapping("/createapp/getdetails/{appId}")
	public LoadAppId getByAppId(@PathVariable Integer appId);

}
