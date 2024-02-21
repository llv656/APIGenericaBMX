package com.sssolutions.bmx.APIGenericaBMX.API.service.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sssolutions.bmx.APIGenericaBMX.BD.dao.IUserExampleRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RestBMXTemplateService {

	private RestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	public <T> T executeRequest(String apiUrl, HttpHeaders headers, String requestBody,
			HttpMethod httpMethod, T clazzModel, Object... uriVariables) {
		
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
		
		ResponseEntity<T> response = (ResponseEntity<T>) restTemplate.exchange(
			apiUrl,
			httpMethod,
			requestEntity,
			clazzModel.getClass(), 
			uriVariables
		);
		
		return response.getBody();
	}
	
}
