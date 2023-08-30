package com.tcs.bindings;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class EligibleDetailsDto {
	
	String planName;
	String planStatus;
	LocalDate planStartDate;
	LocalDate planEndDate;
	Integer benefitAmount;
	String denialReason;

}
