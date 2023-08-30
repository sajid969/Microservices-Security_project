package com.tcs.externalDto;

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

public class EducationDto {
	
	Integer caseNumber;
	String highestQualification;
	Integer graduationYear;

}
