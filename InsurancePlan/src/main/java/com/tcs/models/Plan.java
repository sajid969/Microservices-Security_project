package com.tcs.models;

import java.time.LocalDate;

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
@NoArgsConstructor
@AllArgsConstructor

public class Plan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer pId;
	String planName;
	LocalDate startDate;
	LocalDate endDate;
	String createdBy;
	String updatedBy;
	
	Integer pcID;

}
