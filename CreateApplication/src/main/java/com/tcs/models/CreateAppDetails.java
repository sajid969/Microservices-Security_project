package com.tcs.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data

public class CreateAppDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer appId;
	Integer userId;
	String fullName;
	LocalDate DOB;
	String city;
	String state;
	String country;
	Integer ssNumber;

}
