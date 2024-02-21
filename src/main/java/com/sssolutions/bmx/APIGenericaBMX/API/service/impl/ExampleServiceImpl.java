package com.sssolutions.bmx.APIGenericaBMX.API.service.impl;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.APIGenericaBMX.API.service.IExampleService;
import com.sssolutions.bmx.APIGenericaBMX.API.service.utils.ServiceUtils;
import com.sssolutions.bmx.APIGenericaBMX.BD.dao.IUserExampleRepository;
import com.sssolutions.bmx.dto.ResponseServiceDTO;
import com.sssolutions.bmx.dto.ResponseDaoDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExampleServiceImpl implements IExampleService{
	
	private static final Logger LOGGER = LogManager.getLogger(ExampleServiceImpl.class);
	private IUserExampleRepository userExampleDAO;
	private ServiceUtils serviceUtils;

	@Override
	public ResponseServiceDTO executeAddUserService(
			CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO,
			RequestAddUserExampleModel body
	) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		
		Supplier<ResponseDaoDTO> callback = () -> {	
			LOGGER.info("\t\tEmpieza sanitización de campos");
			body.sanitizeFields();
			
			LOGGER.info("\t\tRegistro de usuario");
			ResponseDaoDTO responseDAO = userExampleDAO.addClient(body, credentiaslAsyncResponseDAO);
			return responseDAO;
		};
		
		ResponseServiceDTO responseDTO = serviceUtils.executeService.apply(method, HttpStatus.CREATED, callback);
		
		return responseDTO;
	}

	@Override
	public ResponseServiceDTO executeGetUsersService(CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseServiceDTO executeGetUserByIdService(
			CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO,
			Integer id
	) {	
		String method = new Object(){}.getClass().getEnclosingMethod().getName();

		Supplier<ResponseDaoDTO> callback = () -> {	
			LOGGER.info("\t\tObtener usuario");
			ResponseDaoDTO responseDAO = userExampleDAO.getClient(id, credentiaslAsyncResponseDAO);
			return responseDAO;
		};
		
		ResponseServiceDTO responseDTO = serviceUtils.executeService.apply(method, HttpStatus.OK, callback);
		
		return responseDTO;
	}

	@Override
	public ResponseServiceDTO executeUpdateUserByIdService(CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO, RequestAddUserExampleModel body, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseServiceDTO executeUpdateTypeUserByIdService(CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO, int userType,
			int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseServiceDTO executeDeleteUserByIdService(CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
