package com.tcs.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.models.PlanCategory;
import com.tcs.repositories.PlanCategoryRepo;

@Service
public class PlanCategoryServiceImpl implements PlanCategoryService {
	
	@Autowired
	public PlanCategoryRepo planCategoryRepo;

	@Override
	public boolean savePlanCategory(PlanCategory planCategory) {
		PlanCategory save = planCategoryRepo.save(planCategory);
		return save.getPcID()!=null;
	}

	@Override
	public Map<Integer, String> getPlanCategory() {
		List<PlanCategory> findAll = planCategoryRepo.findAll();
		Map<Integer,String> pc = new HashMap<>();
		findAll.forEach(fc -> pc.put(fc.getPcID(), fc.getPlanCategoryName()));
		return pc;
	}

}
