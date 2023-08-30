package com.tcs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.models.PlanCategory;
import com.tcs.services.PlanCategoryService;

@RestController
@RequestMapping("/insurance")
public class PlanCategoryController {
	@Autowired
	public PlanCategoryService planCategoryService;
	
	@GetMapping("/plancategory")
	public ResponseEntity<Map<Integer, String>> getUsPlanCategory(){
		Map<Integer, String> planCategory = planCategoryService.getPlanCategory();
		return new ResponseEntity<Map<Integer, String>>(planCategory, HttpStatus.OK);
	}
	
	@PostMapping("/plancategory")
	public ResponseEntity<String> saveUsPlanCategory(@RequestBody PlanCategory planCategory){
		boolean savePlanCategory = planCategoryService.savePlanCategory(planCategory);
		if(savePlanCategory) {
			String message="saved successfully";
			return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
		}
		else {
			String message="not saved successfully";
			return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);

		}
	}

}
