package com.tcs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.dto.PlanDto;
import com.tcs.models.Plan;
import com.tcs.services.PlanServices;

@RestController
@RequestMapping("/insurance")
public class PlanController {
	
	@Autowired
	public PlanServices planServices;
	
	@GetMapping("/plan")
	public ResponseEntity<List<PlanDto>> getPlanServices(){
		List<PlanDto> allPlanServices = planServices.getAllPlanServices();
		return new ResponseEntity<List<PlanDto>>(allPlanServices, HttpStatus.OK);
	}
	
	@PostMapping("/plan")
	public Plan savePlanServices(@RequestBody Plan plan) {
		Plan saveAllPlanServices = planServices.saveAllPlanServices(plan);
		return saveAllPlanServices;
	}
	
	@PatchMapping("/planupdate")
	public Plan updateServices(@RequestBody Plan plan) {
		Plan saveAllPlanServices = planServices.saveAllPlanServices(plan);
		return saveAllPlanServices;
	}
	@DeleteMapping("/plandelete/{Id}")
	public String deleteServices(@PathVariable Integer Id) {
		String deletePlanServices = planServices.deletePlanServices(Id);
		return deletePlanServices;
	}
	@GetMapping("/plan/{Id}")
	public Optional<Plan> getOneService(@PathVariable Integer Id) {
		Optional<Plan> onlyOneService = planServices.getOnlyOneService(Id);
		return onlyOneService;
	}

}
