package com.tcs.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.externalDto.ChildDto;
import com.tcs.externalDto.DcRequestDto;
import com.tcs.externalDto.EducationDto;
import com.tcs.externalDto.IncomeDto;
import com.tcs.externalDto.LoadAppId;
import com.tcs.externalDto.LoadCaseNumber;
import com.tcs.externalDto.LoadPlanId;
import com.tcs.externalservices.CreateApplicationServices;
import com.tcs.externalservices.DataCollectionServices;
import com.tcs.externalservices.InsurancePlanServices;
import com.tcs.models.EligibleDetails;
import com.tcs.repositories.EligibleDetailsRepo;

@Service
public class EligibleDetailsServiceImpl implements EligibleDetailsService {

	@Autowired
	public EligibleDetailsRepo eligibleDetailsRepo;

	@Autowired
	private DataCollectionServices dataCollectionServices;

	@Autowired
	public CreateApplicationServices createApplicationServices;

	@Autowired
	public InsurancePlanServices insurancePlanServices;

	@Override
	public EligibleDetails saveEligibilityDetails(Integer caseNumber) {
		LoadCaseNumber byCaseNumber = dataCollectionServices.getByCaseNumber(caseNumber);// caseNumber
		if (byCaseNumber != null) {
			EligibleDetails findByCaseNumber = eligibleDetailsRepo.findByCaseNumber(caseNumber);
			if (findByCaseNumber == null) {
				return eligibleDetailsRepo.save(createEligibilityDetails(caseNumber));
			}
			else {
				EligibleDetails createEligibilityDetails = createEligibilityDetails(caseNumber);
				findByCaseNumber.setCaseNumber(createEligibilityDetails.getCaseNumber());
				findByCaseNumber.setHolderName(createEligibilityDetails.getHolderName());
				findByCaseNumber.setHolderSsn(createEligibilityDetails.getHolderSsn());
				findByCaseNumber.setPlanName(createEligibilityDetails.getPlanName());
				findByCaseNumber.setPlanStatus(createEligibilityDetails.getPlanStatus());
				findByCaseNumber.setPlanStartDate(createEligibilityDetails.getPlanStartDate());
				findByCaseNumber.setPlanEndDate(createEligibilityDetails.getPlanEndDate());
				findByCaseNumber.setBenefitAmount(createEligibilityDetails.getBenefitAmount());
				findByCaseNumber.setDenialReason(createEligibilityDetails.getDenialReason());
				return eligibleDetailsRepo.save(findByCaseNumber);
			}
			
			
			
		}
	
		return null;
	}

	@Override
	public LoadAppId getAppDetails(Integer appId) {
		return createApplicationServices.getByAppId(appId);
	}

	@Override
	public LoadPlanId getPlanDetails(Integer planId) {
		return insurancePlanServices.getPlanId(planId);
	}

	@Override
	public EligibleDetails createEligibilityDetails(Integer caseNumber) {
		LoadCaseNumber byCaseNumber = dataCollectionServices.getByCaseNumber(caseNumber);// caseNumber
		if (byCaseNumber != null) {
			

				Integer appId = byCaseNumber.getAppId();// AppId
				Integer planId = byCaseNumber.getPlanId();// PlanId
				LoadAppId appDetails = createApplicationServices.getByAppId(appId);// AppDetails
				LoadPlanId planDetails = insurancePlanServices.getPlanId(planId);// PlanDetails

				EligibleDetails eligibleDetails = new EligibleDetails();
				eligibleDetails.setCaseNumber(caseNumber);
				eligibleDetails.setHolderName(appDetails.getFullName());
				eligibleDetails.setHolderSsn(appDetails.getSsNumber());
				eligibleDetails.setPlanName(planDetails.getPlanName());
				eligibleDetails.setPlanStartDate(planDetails.getStartDate());
				eligibleDetails.setPlanEndDate(planDetails.getEndDate());

				DcRequestDto dcDetails = dataCollectionServices.getDcDetails(caseNumber);
				// get plan name
				DcRequestDto dcRequestDto = new DcRequestDto();
				dcRequestDto.setCaseNumber(caseNumber);

				// get education

				EducationDto education = dcDetails.getEducation();

				// get income
				IncomeDto income = dcDetails.getIncome();

				// get kids count
				List<ChildDto> children = dcDetails.getChildren();

				// get citizen age
				LocalDate dob = appDetails.getDOB();
				LocalDate currentDate = LocalDate.now();

				// getDetails
				String planName = dcDetails.getPlanName();
				Integer empIncome = income.getEmpIncome();
				Integer propertyIncome = income.getPropertyIncome();
				Integer graduationYear = education.getGraduationYear();
				// Integer kids = children.size();
				Integer holderAge = (currentDate.getYear() - dob.getYear());
				Integer kids = 0;
				for (ChildDto c : children) {
					kids++;

				}

				if (planName.equals("SNAP")) {
					if (empIncome <= 300) {
						String message = "Eligible";
						eligibleDetails.setPlanStatus(message);
						eligibleDetails.setDenialReason("NA");
						eligibleDetails.setBenefitAmount(50);
						return eligibleDetails;
					} else {
						String message = "Not Eligible";
						eligibleDetails.setPlanStatus(message);
						eligibleDetails.setDenialReason("Your Income is Greater than 300");
						eligibleDetails.setBenefitAmount(0);
						return eligibleDetails;
					}

				} else if (planName.equals("CCAP")) {
					if (empIncome <= 300) {
						if (kids > 0) {
							boolean flag = true;
							for (ChildDto c : children) {
								String childAge = c.getChildAge();
								int intValue = Integer.parseInt(childAge);
								if (intValue >= 16) {
									flag = false;
								}
							}
							if (flag) {
								eligibleDetails.setPlanStatus("Eligible");
								eligibleDetails.setDenialReason("NA");
								eligibleDetails.setBenefitAmount(50);
								return eligibleDetails;
							} else {
								eligibleDetails.setPlanStatus("Not Eligible");
								eligibleDetails.setDenialReason("Your Kid age is not less than 16 ");
								eligibleDetails.setBenefitAmount(0);
								return eligibleDetails;
							}
						} else {
							eligibleDetails.setPlanStatus("Not Eligible");
							eligibleDetails.setDenialReason("You dont have kids ");
							eligibleDetails.setBenefitAmount(0);
							return eligibleDetails;
						}

					} else {
						eligibleDetails.setPlanStatus("Not Eligible");
						eligibleDetails.setDenialReason("Your Income is not Greater than 300 ");
						eligibleDetails.setBenefitAmount(0);
						return eligibleDetails;
					}

				} else if (planName.equals("Medicaid")) {
					if (empIncome <= 300) {
						if (propertyIncome == 0) {
							eligibleDetails.setPlanStatus("Eligible");
							eligibleDetails.setDenialReason("NA");
							eligibleDetails.setBenefitAmount(50);
							return eligibleDetails;
						} else {
							eligibleDetails.setPlanStatus("Not Eligible");
							eligibleDetails.setDenialReason("Your Property Income is not zero ");
							eligibleDetails.setBenefitAmount(0);
							return eligibleDetails;
						}

					} else {
						eligibleDetails.setPlanStatus("Not Eligible");
						eligibleDetails.setDenialReason("Your Income is not Greater than 300 ");
						eligibleDetails.setBenefitAmount(0);
						return eligibleDetails;
					}
				} else if (planName.equals("Medicare")) {
					if (holderAge >= 65) {
						eligibleDetails.setPlanStatus("Eligible");
						eligibleDetails.setDenialReason("NA");
						eligibleDetails.setBenefitAmount(50);
						return eligibleDetails;
					} else {
						eligibleDetails.setPlanStatus("Not Eligible");
						eligibleDetails.setDenialReason("Your age is not eligible");
						eligibleDetails.setBenefitAmount(0);
						return eligibleDetails;
					}
				} else if (planName.equals("NJW")) {
					if (graduationYear == null) {
						eligibleDetails.setPlanStatus("Eligible");
						eligibleDetails.setDenialReason("NA");
						eligibleDetails.setBenefitAmount(50);
						return eligibleDetails;
					} else {
						eligibleDetails.setPlanStatus("Not Eligible");
						eligibleDetails.setDenialReason("You are educated");
						eligibleDetails.setBenefitAmount(0);
						return eligibleDetails;
					}

				} else {
					return eligibleDetails;
				}
			
		}
		return null;
	}

}
