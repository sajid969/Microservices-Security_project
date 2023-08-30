package com.tcs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.models.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer> {

}
