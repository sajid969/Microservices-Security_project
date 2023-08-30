package com.tcs.services;

import java.util.Map;

import com.tcs.models.PlanCategory;

public interface PlanCategoryService {
	
	public boolean savePlanCategory(PlanCategory planCategory);
	
	public Map<Integer, String> getPlanCategory();

}
