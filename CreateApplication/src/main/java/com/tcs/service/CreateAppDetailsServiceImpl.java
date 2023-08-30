package com.tcs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tcs.bindings.CreateAppDetailsDto;
import com.tcs.bindings.UserResponse;
import com.tcs.models.CreateAppDetails;
import com.tcs.repositories.CreateAppDetailsRepo;

@Service
public class CreateAppDetailsServiceImpl implements CreateAppDetailsService {

	@Autowired
	public CreateAppDetailsRepo createAppDetailsRepo;
	
	@Autowired
	public RestTemplate restTemplate;

	@Override
	public CreateAppDetailsDto saveApplicationDetails(CreateAppDetailsDto createAppDetailsDto) {

		String apiUrl = "http://localhost:8181/city/{ssn}";
		String apiUrlUserId = "http://INSURANCEPLANAPPLICATION/insurance/user/{id}";
		ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, String.class,
				createAppDetailsDto.getSsNumber());
		String body = response.getBody();
		ResponseEntity<UserResponse> userResponse = restTemplate.exchange(apiUrlUserId, HttpMethod.GET, null,
				UserResponse.class, createAppDetailsDto.getUserId());
		UserResponse body2 = userResponse.getBody();
		
			if (body2 != null&&body.equals("Newyork city")) {
				Optional<CreateAppDetails> findByUserId = createAppDetailsRepo.findByUserId(body2.getUserID());
				Optional<CreateAppDetails> findBySsNumber = createAppDetailsRepo.findBySsNumber(createAppDetailsDto.getSsNumber());
				if(findByUserId.isEmpty()&&findBySsNumber.isEmpty()) {
					CreateAppDetails cad = new CreateAppDetails();
					cad.setFullName(createAppDetailsDto.getFullName());
					cad.setCity(createAppDetailsDto.getCity());
					cad.setState(createAppDetailsDto.getState());
					cad.setCountry(createAppDetailsDto.getCountry());
					cad.setSsNumber(createAppDetailsDto.getSsNumber());
					cad.setUserId(body2.getUserID());
					CreateAppDetails save = createAppDetailsRepo.save(cad);
					if (save != null) {
						createAppDetailsDto.setMessage("Data Saved Successfully");
						createAppDetailsDto.setAppId(save.getAppId());
						return createAppDetailsDto;
					}
				}
				if(findBySsNumber.isPresent()) {
					CreateAppDetailsDto cappDetail = new CreateAppDetailsDto();
					cappDetail.setMessage("Your SSN Number already exists");
					cappDetail.setFullName(findBySsNumber.get().getFullName());
					cappDetail.setSsNumber(findBySsNumber.get().getSsNumber());
				
					return cappDetail;
				}
			}

		return null;
	}

	@Override
	public Optional<CreateAppDetails> getApplicationDetails(Integer Id) {
		Optional<CreateAppDetails> findById = createAppDetailsRepo.findById(Id);
		return findById;
	}

}
