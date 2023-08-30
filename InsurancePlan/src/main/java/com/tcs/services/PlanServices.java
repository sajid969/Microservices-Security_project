package com.tcs.services;

import java.util.List;
import java.util.Optional;

import com.tcs.dto.PlanDto;
import com.tcs.models.Plan;

public interface PlanServices {
	
	List<PlanDto> getAllPlanServices();
	
	Plan saveAllPlanServices(Plan plan);
	
	Plan updatePlanServices(Plan plan);
	
	String deletePlanServices(Integer Id);
	
	Optional<Plan> getOnlyOneService(Integer Id);
	
}
