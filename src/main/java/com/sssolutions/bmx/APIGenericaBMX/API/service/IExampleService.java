package com.sssolutions.bmx.APIGenericaBMX.API.service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseServiceDTO;

public interface IExampleService {

	public ResponseServiceDTO executeAddUserService(CompletableFuture<ResponseServiceDTO> credentialsDAO, RequestAddUserExampleModel body);
	
	public ResponseServiceDTO executeGetUsersService(Map<String, String> credentialsDAO);
	
	public ResponseServiceDTO executeGetUserByIdService(Map<String, String> credentialsDAO, Integer id);

	public ResponseServiceDTO executeUpdateUserByIdService(Map<String, String> credentialsDAO, RequestAddUserExampleModel body, int id);
	
	public ResponseServiceDTO executeUpdateTypeUserByIdService(Map<String, String> credentialsDAO, int userType, int id);
	
	public ResponseServiceDTO executeDeleteUserByIdService(Map<String, String> credentialsDAO, int id);
}
