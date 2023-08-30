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
@AllArgsConstructor
@NoArgsConstructor

public class EligibleDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer edTraceId;
	Integer caseNumber;
	String holderName;
	Integer holderSsn;
	String planName;
	String planStatus;
	LocalDate planStartDate;
	LocalDate planEndDate;
	Integer benefitAmount;
	String denialReason;

}
