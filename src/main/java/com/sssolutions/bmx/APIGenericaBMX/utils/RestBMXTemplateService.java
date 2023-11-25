package com.sssolutions.bmx.APIGenericaBMX.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestBMXTemplateService {

	@Autowired
	private RestTemplate restTemplate;
	
	public RestBMXTemplateService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

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
