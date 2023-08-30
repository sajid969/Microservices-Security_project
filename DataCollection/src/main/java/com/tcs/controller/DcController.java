package com.tcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.bindings.ChildRequestDto;
import com.tcs.bindings.DcRequestDto;
import com.tcs.bindings.EducationDto;
import com.tcs.bindings.IncomeDto;
import com.tcs.bindings.SaveAllDtoResponse;
import com.tcs.models.DcCase;
import com.tcs.services.DcService;

@RestController
@RequestMapping("/datacollect")
public class DcController {
	
	@Autowired
	public DcService dcService;
	
	@PostMapping("/dccase")
	public ResponseEntity<String> saveCaseNumberDetails(@RequestBody DcCase dcCase){
		String loadCaseNumber = dcService.loadCaseNumber(dcCase);
		return new ResponseEntity<String>(loadCaseNumber,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/education")
	public ResponseEntity<String> educationDetails(@RequestBody EducationDto educationDto){
		String saveEducationDetails = dcService.saveEducationDetails(educationDto);
		return new ResponseEntity<String>(saveEducationDetails,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/income")
	public ResponseEntity<String> incomeDetails(@RequestBody IncomeDto incomeDto){
		String saveIncomeDetails = dcService.saveIncomeDetails(incomeDto);
		return new ResponseEntity<String>(saveIncomeDetails,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/child")
	public ResponseEntity<String> childDetails(@RequestBody ChildRequestDto childRequestDto){
		String saveChildDetails = dcService.saveChildDetails(childRequestDto);
		return new ResponseEntity<String>(saveChildDetails,HttpStatus.ACCEPTED);	
	}
	
	@PostMapping("/savedc")
	public ResponseEntity<SaveAllDtoResponse> dataCollection(@RequestBody DcRequestDto dcRequestDto){
		SaveAllDtoResponse saveAllDc = dcService.saveAllDc(dcRequestDto);
		return new ResponseEntity<SaveAllDtoResponse>(saveAllDc,HttpStatus.ACCEPTED);
	}
	@GetMapping("/preview/{caseNumber}")
	public ResponseEntity<DcRequestDto> getSummary(@PathVariable Integer caseNumber){
		DcRequestDto giveAllDc = dcService.giveAllDc(caseNumber);
		return new ResponseEntity<DcRequestDto>(giveAllDc,HttpStatus.OK);
	}
	
	@GetMapping("/getappid/{id}")
	public ResponseEntity<DcCase> getAppId(@PathVariable Integer id){
		DcCase appId = dcService.getAppId(id);
		return new ResponseEntity<DcCase>(appId,HttpStatus.OK);
	}

}
