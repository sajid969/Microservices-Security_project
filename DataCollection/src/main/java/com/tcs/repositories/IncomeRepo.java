package com.tcs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.models.Income;


public interface IncomeRepo extends JpaRepository<Income, Integer> {
	Income findByCaseNumber(Integer caseNumber);
}
