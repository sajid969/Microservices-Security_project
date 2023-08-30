package com.tcs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.dto.PlanDto;
import com.tcs.models.Plan;
import com.tcs.models.PlanCategory;
import com.tcs.repositories.PlanCategoryRepo;
import com.tcs.repositories.PlanRepo;

@Service
public class PlanServicesImpl implements PlanServices {

	@Autowired
	public PlanRepo planRepo;
	@Autowired
	public PlanCategoryRepo planCategory;

	@Override
	public List<PlanDto> getAllPlanServices() {
		List<PlanDto> planDto = new ArrayList<>();
		List<Plan> findAll = planRepo.findAll();

		for (Plan pr : findAll) {
			PlanDto p = new PlanDto();
			BeanUtils.copyProperties(pr, p);
			planDto.add(p);
			if(pr.getPId()!=null) {
				Optional<PlanCategory> findById = planCategory.findById(pr.getPId());
				if (findById.isPresent()) {
					p.setPlanCategory(findById.get().getPlanCategoryName());
				}
			}
			
		}
		return planDto;
	}

	@Override
	public Plan saveAllPlanServices(Plan plan) {
		Plan save = planRepo.save(plan);
		return save;
	}

	@Override
	public Plan updatePlanServices(Plan plan) {
		Plan save = planRepo.save(plan);
		return save;
	}

	@Override
	public String deletePlanServices(Integer Id) {
		planRepo.deleteById(Id);
		return "delete succesfully";
	}

	@Override
	public Optional<Plan> getOnlyOneService(Integer Id) {
		Optional<Plan> findById = planRepo.findById(Id);
		return findById;
	}

}
