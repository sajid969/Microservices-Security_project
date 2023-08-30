package com.tcs.externalDto;

import java.util.List;

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

public class DcRequestDto {
	
	Integer caseNumber;
	String planName;
	List<ChildDto> children;
	EducationDto education;
	IncomeDto income;

}
