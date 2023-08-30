package com.tcs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tcs.bindings.ChildDto;
import com.tcs.bindings.ChildRequestDto;
import com.tcs.bindings.CreateAppResponse;
import com.tcs.bindings.DcRequestDto;
import com.tcs.bindings.EducationDto;
import com.tcs.bindings.IncomeDto;
import com.tcs.bindings.PlanResponse;
import com.tcs.bindings.SaveAllDtoResponse;
import com.tcs.models.Children;
import com.tcs.models.DcCase;
import com.tcs.models.Education;
import com.tcs.models.Income;
import com.tcs.repositories.ChildRepo;
import com.tcs.repositories.DcCaseRepo;
import com.tcs.repositories.EducationRepo;
import com.tcs.repositories.IncomeRepo;
import com.tcs.repositories.UserRepository;

@Service

public class DcServiceImpl implements DcService {
	@Autowired
	public UserRepository userRepository;
	@Autowired
	public DcCaseRepo dcCaseRepo;

	@Autowired
	public EducationRepo educationRepo;

	@Autowired
	public IncomeRepo incomeRepo;

	@Autowired
	public ChildRepo childRepo;

	private final RestTemplate restTemplate;

	@Autowired
	public DcServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public String loadCaseNumber(DcCase dcCase) {
		String message = "";
		String apiUrlPlan = "http://INSURANCEPLANAPPLICATION/insurance/plan/{Id}";
		String apiUrlApp = "http://CREATEAPPLICATION/createapp/getdetails/{appId}";

		ResponseEntity<PlanResponse> planResponse = restTemplate.exchange(apiUrlPlan, HttpMethod.GET, null,
				PlanResponse.class, dcCase.getPlanId());
		PlanResponse body = planResponse.getBody();

		ResponseEntity<CreateAppResponse> createAppResponse = restTemplate.exchange(apiUrlApp, HttpMethod.GET, null,
				CreateAppResponse.class, dcCase.getAppId());
		CreateAppResponse body2 = createAppResponse.getBody();

		if (body2 != null && body != null) {

			Optional<DcCase> findByAppId = dcCaseRepo.findByAppId(dcCase.getAppId());
			if (findByAppId.isEmpty()) {
				Optional<DcCase> findByPlanIdAndAppId = dcCaseRepo.findByPlanIdAndAppId(dcCase.getPlanId(),
						dcCase.getAppId());
				if (findByPlanIdAndAppId.isEmpty()) {
					DcCase save = dcCaseRepo.save(dcCase);
					message = String.format("case number %d", save.getCaseNumber());
					return message;
				} else {
					message = "PlanId or AppId already exists";
					return message;
				}

			}

		} else {
			message = "PlanId or AppId doesn't exists";
			return message;
		}

		return null;
	}

	@Override
	public String saveEducationDetails(EducationDto educationDto) {
		String message = "not saved";
		Education ed = new Education();
		BeanUtils.copyProperties(educationDto, ed);
		Education save = educationRepo.save(ed);
		if (save.getEducationId() != null) {
			message = "saved successfully";
		}
		return message;
	}

	@Override
	public String saveIncomeDetails(IncomeDto incomeDto) {
		String message = "not saved";
		Income ic = new Income();
		BeanUtils.copyProperties(incomeDto, ic);
		Income save = incomeRepo.save(ic);
		if (save != null) {
			message = "saved successfully";
		}
		return message;
	}

	@Override
	public String saveChildDetails(ChildRequestDto childRequestDto) {
		String message = "not saved";
		Integer caseNumber = childRequestDto.getCaseNumber();
		List<ChildDto> children = childRequestDto.getChildren();
		for (ChildDto child : children) {
			Children cd = new Children();
			cd.setChildName(child.getChildName());
			cd.setChildAge(child.getChildAge());
			cd.setChildAadhar(child.getChildAadhar());
			cd.setCaseNumber(caseNumber);
			childRepo.save(cd);
			message = "saved Succesfully";
		}
		return message;
	}

	@Override
	public SaveAllDtoResponse saveAllDc(DcRequestDto dcRequestDto) {
		String message = "";
		Integer caseNumber = dcRequestDto.getCaseNumber();
		if (giveAllDc(caseNumber) != null) {
			message = "case number already exists";
			SaveAllDtoResponse sv = new SaveAllDtoResponse();
			sv.setData(giveAllDc(caseNumber));
			sv.setMessage(message);

			return sv;
		}
		EducationDto education = dcRequestDto.getEducation();
		List<ChildDto> children = dcRequestDto.getChildren();
		IncomeDto income = dcRequestDto.getIncome();

		Education ed = new Education();
		ed.setCaseNumber(caseNumber);
		education.setCaseNumber(caseNumber);
		ed.setGraduationYear(education.getGraduationYear());
		ed.setHighestQualification(education.getHighestQualification());
		Education save = educationRepo.save(ed);

		Income ic = new Income();
		ic.setCaseNumber(caseNumber);
		income.setCaseNumber(caseNumber);
		ic.setEmpIncome(income.getEmpIncome());
		ic.setPropertyIncome(income.getPropertyIncome());
		Income save2 = incomeRepo.save(ic);

		for (ChildDto child : children) {
			Children cd = new Children();
			cd.setCaseNumber(caseNumber);
			cd.setChildName(child.getChildName());
			cd.setChildAge(child.getChildAge());
			cd.setChildAadhar(child.getChildAadhar());
			childRepo.save(cd);

		}

		if (save != null || save2 != null) {
			SaveAllDtoResponse sv = new SaveAllDtoResponse();

			Optional<DcCase> findById = dcCaseRepo.findById(caseNumber);
			if (findById.isPresent()) {
				Integer planId = findById.get().getPlanId();
				String planName = userRepository.getStringDataFromDatabase(planId);
				dcRequestDto.setPlanName(planName);
			}
			message = "saved successfully";
			sv.setData(dcRequestDto);
			sv.setMessage(message);

			return sv;
		}
		return null;
	}

	@Override
	public DcRequestDto giveAllDc(Integer caseNumber) {

		DcRequestDto dr = new DcRequestDto();

		Education findByCaseNumber = educationRepo.findByCaseNumber(caseNumber);
		Income findByCaseNumber2 = incomeRepo.findByCaseNumber(caseNumber);
		List<Children> findByCaseNumber3 = childRepo.findByCaseNumber(caseNumber);
		if (findByCaseNumber == null && findByCaseNumber2 == null && findByCaseNumber3.isEmpty()) {
			return null;
		}
		if (findByCaseNumber2 != null) {
			IncomeDto ic = new IncomeDto();
			ic.setCaseNumber(findByCaseNumber2.getCaseNumber());
			ic.setEmpIncome(findByCaseNumber2.getEmpIncome());
			ic.setPropertyIncome(findByCaseNumber2.getPropertyIncome());
			dr.setIncome(ic);

		}
		if (findByCaseNumber3 != null) {
			List<ChildDto> lcd = new ArrayList<>();

			for (Children child : findByCaseNumber3) {
				ChildDto cd = new ChildDto();
				cd.setChildName(child.getChildName());
				cd.setChildAge(child.getChildAge());
				cd.setChildAadhar(child.getChildAadhar());
				lcd.add(cd);
			}
			dr.setChildren(lcd);
		}
		if (findByCaseNumber != null) {
			EducationDto ed = new EducationDto();
			ed.setCaseNumber(findByCaseNumber.getCaseNumber());
			ed.setHighestQualification(findByCaseNumber.getHighestQualification());
			ed.setGraduationYear(findByCaseNumber.getGraduationYear());
			dr.setEducation(ed);
		}

		Optional<DcCase> findById = dcCaseRepo.findById(caseNumber);
		if (findById.isPresent()) {
			Integer planId = findById.get().getPlanId();
			String planName = userRepository.getStringDataFromDatabase(planId);
			dr.setPlanName(planName);

		}

		dr.setCaseNumber(caseNumber);
		return dr;
	}

	@Override
	public DcCase getAppId(Integer Id) {
		Optional<DcCase> findById = dcCaseRepo.findById(Id);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
}
