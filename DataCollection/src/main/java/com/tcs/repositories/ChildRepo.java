package com.tcs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.models.Children;

public interface ChildRepo extends JpaRepository<Children, Integer>{
	
	List<Children> findByCaseNumber(Integer caseNumber);

}
