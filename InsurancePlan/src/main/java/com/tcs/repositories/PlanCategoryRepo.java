package com.tcs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.models.PlanCategory;

public interface PlanCategoryRepo extends JpaRepository<PlanCategory, Integer> {

}
