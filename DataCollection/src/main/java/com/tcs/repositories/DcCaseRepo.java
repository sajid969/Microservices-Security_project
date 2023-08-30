package com.tcs.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.models.DcCase;


public interface DcCaseRepo extends JpaRepository<DcCase, Integer> {
	
	
	DcCase findByPlanId(Integer planId);
	
	Optional<DcCase> findByAppId(Integer appId);
	Optional<DcCase> findByPlanIdAndAppId(Integer planId,Integer appId);
}
