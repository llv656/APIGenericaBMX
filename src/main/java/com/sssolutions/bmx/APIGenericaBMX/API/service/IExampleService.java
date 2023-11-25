package com.sssolutions.bmx.APIGenericaBMX.API.service;

import java.util.Map;

import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseDTO;
import com.sssolutions.bmx.APIGenericaBMX.model.APIModel;

public interface IExampleService {

	public ResponseDTO executeAddUserService(Map<String, String> credentialsDAO, APIModel propertiesRequest, RequestAddUserExampleModel body);
	
	public ResponseDTO executeGetUsersService(Map<String, String> credentialsDAO, APIModel propertiesRequest);
	
	public ResponseDTO executeGetUserByIdService(Map<String, String> credentialsDAO, APIModel propertiesRequest, int id);

	public ResponseDTO executeUpdateUserByIdService(Map<String, String> credentialsDAO, APIModel propertiesRequest, 
			RequestAddUserExampleModel body, int id);
	
	public ResponseDTO executeUpdateTypeUserByIdService(Map<String, String> credentialsDAO, APIModel propertiesRequest, 
			int userType, int id);
	
	public ResponseDTO executeDeleteUserByIdService(Map<String, String> credentialsDAO, APIModel propertiesRequest, int id);
}
