package com.tcs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SocialMediaUsers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer userId;
	String userName;
	String password;
	String email;
	String status;
	String role;
	
}
