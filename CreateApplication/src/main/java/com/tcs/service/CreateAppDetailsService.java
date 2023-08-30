package com.tcs.service;

import java.util.Optional;

import com.tcs.bindings.CreateAppDetailsDto;
import com.tcs.models.CreateAppDetails;

public interface CreateAppDetailsService {
	
	CreateAppDetailsDto saveApplicationDetails(CreateAppDetailsDto createAppDetailsDto);
	
	Optional<CreateAppDetails> getApplicationDetails(Integer Id);

}
