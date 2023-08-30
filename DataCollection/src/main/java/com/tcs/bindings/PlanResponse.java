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

public class PlanResponse {
	
	String planName;
	LocalDate startDate;
	LocalDate endDate;
	String createdBy;
	String updatedBy;
	Integer pcID;
	Integer pid;

}
