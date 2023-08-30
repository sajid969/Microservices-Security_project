package com.tcs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.models.Education;


public interface EducationRepo extends JpaRepository<Education, Integer> {
	Education findByCaseNumber(Integer caseNumber);
}
