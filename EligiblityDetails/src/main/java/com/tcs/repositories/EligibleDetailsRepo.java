package com.tcs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.models.EligibleDetails;


public interface EligibleDetailsRepo extends JpaRepository<EligibleDetails, Integer> {
	EligibleDetails findByCaseNumber(Integer caseNumber);
	
}
