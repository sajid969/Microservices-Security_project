package com.tcs.repositaries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.bindings.UpdatePasswordDto;
import com.tcs.model.SocialMediaUsers;

public interface SocialMediaUsersRepo extends JpaRepository<SocialMediaUsers, Integer> {
	
	Optional<SocialMediaUsers> findByUserName(String username);
	
	Optional<SocialMediaUsers> findByUserNameAndPassword(String username,String password);

}
