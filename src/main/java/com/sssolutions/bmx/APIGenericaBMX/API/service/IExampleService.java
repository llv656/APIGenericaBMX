package com.sssolutions.bmx.APIGenericaBMX.API.service;

import java.util.concurrent.CompletableFuture;

import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.dto.ResponseServiceDTO;

public interface IExampleService {

	public ResponseServiceDTO executeAddUserService(CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO, RequestAddUserExampleModel body);
	
	public ResponseServiceDTO executeGetUsersService(CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO);
	
	public ResponseServiceDTO executeGetUserByIdService(CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO, Integer id);

	public ResponseServiceDTO executeUpdateUserByIdService(CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO, RequestAddUserExampleModel body, int id);
	
	public ResponseServiceDTO executeUpdateTypeUserByIdService(CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO, int userType, int id);
	
	public ResponseServiceDTO executeDeleteUserByIdService(CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO, int id);
}
