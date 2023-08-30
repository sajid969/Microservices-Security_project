package com.tcs.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PlanDto {
	
	String planName;
	LocalDate startDate;
	LocalDate endDate;
	String planCategory;

}
