package com.tcs.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.models.CreateAppDetails;


public interface CreateAppDetailsRepo extends JpaRepository<CreateAppDetails, Integer> {
	
	Optional<CreateAppDetails> findBySsNumber(Integer ssNumber);
	
	Optional<CreateAppDetails> findByUserId(Integer userId);

}
