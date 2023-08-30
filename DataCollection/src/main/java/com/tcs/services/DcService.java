package com.tcs.services;

import com.tcs.bindings.ChildRequestDto;
import com.tcs.bindings.DcRequestDto;
import com.tcs.bindings.EducationDto;
import com.tcs.bindings.IncomeDto;
import com.tcs.bindings.SaveAllDtoResponse;
import com.tcs.models.DcCase;

public interface DcService {
	
	String loadCaseNumber(DcCase dcCase);
	
	String saveEducationDetails(EducationDto educationDto);
	
	String saveIncomeDetails(IncomeDto incomeDto);
	
	String saveChildDetails(ChildRequestDto childRequestDto);
	
	SaveAllDtoResponse saveAllDc(DcRequestDto dcRequestDto);
	
	DcRequestDto giveAllDc(Integer caseNumber);
	
	DcCase getAppId(Integer Id);
}
