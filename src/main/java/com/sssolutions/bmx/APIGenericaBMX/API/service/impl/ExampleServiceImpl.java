package com.sssolutions.bmx.APIGenericaBMX.API.service.impl;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.APIGenericaBMX.API.service.IExampleService;
import com.sssolutions.bmx.APIGenericaBMX.BD.dao.IUserExampleRepository;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseServiceDTO;
import com.sssolutions.bmx.APIGenericaBMX.values.Messages;
import com.sssolutions.bmx.RepositoryBMX.enums.DAOStatus;
import com.sssolutions.bmx.RepositoryBMX.pojo.ResponseDaoDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExampleServiceImpl implements IExampleService{
	
	private static final Logger LOGGER = LogManager.getLogger(ExampleServiceImpl.class);
	private IUserExampleRepository userExampleDAO;

	@Override
	public ResponseServiceDTO executeAddUserService(
			CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO,
			RequestAddUserExampleModel body) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		LOGGER.info("\t\tMethod:".concat(method));
		
		ResponseServiceDTO responseDTO = new ResponseServiceDTO();
		
		LOGGER.info("\t\tEmpieza sanitización de campos");
		body.sanitizeFields();
		
		LOGGER.info("\t\tRegistro de usuario");
		ResponseDaoDTO responseDAO = userExampleDAO.addClient(body, credentiaslAsyncResponseDAO);
		
		LOGGER.info("\t\tConstrucción de respuesta del servicio");
		if (responseDAO.isValid()) {
			responseDTO.setMessage(Messages.OK_001);
			responseDTO.setHttpStatus(HttpStatus.CREATED);
		} else if (!responseDAO.isValid() && responseDAO.getGenericDao() == DAOStatus.err_execution) {
			responseDTO.setMessage(Messages.ERROR_001);
			responseDTO.setDetails(responseDAO.getMessage());
			responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST);
		} else if (!responseDAO.isValid() && responseDAO.getGenericDao() == DAOStatus.err_connection) {
			responseDTO.setMessage(Messages.ERROR_CONNECTION_BD_001);
			responseDTO.setDetails(responseDAO.getMessage());
			responseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		responseDTO.setValid(responseDAO.isValid());
		
		return responseDTO;
	}

	@Override
	public ResponseServiceDTO executeGetUsersService(Map<String, String> headers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseServiceDTO executeGetUserByIdService(
			Map<String, String> responseCredentialsDAO,
			Integer id) {
		
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		LOGGER.info("\t\tMethod:".concat(method));
		
		ResponseServiceDTO responseDTO = new ResponseServiceDTO();
		
		LOGGER.info("\t\tObtener usuario");
		ResponseDaoDTO responseDAO = userExampleDAO.getClient(id, responseCredentialsDAO);
		
		LOGGER.info("\t\tConstrucción de respuesta del servicio");
		if (responseDAO.isValid()) {
			responseDTO.setMessage(Messages.OK_001);
			responseDTO.setResult(responseDAO.getResult());
			responseDTO.setHttpStatus(HttpStatus.OK);
		} else if (!responseDAO.isValid() && responseDAO.getGenericDao() == DAOStatus.err_execution) {
			responseDTO.setMessage(Messages.ERROR_001);
			responseDTO.setDetails(responseDAO.getMessage());
			responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST);
		} else if (!responseDAO.isValid() && responseDAO.getGenericDao() == DAOStatus.err_connection) {
			responseDTO.setMessage(Messages.ERROR_CONNECTION_BD_001);
			responseDTO.setDetails(responseDAO.getMessage());
			responseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		responseDTO.setValid(responseDAO.isValid());
		
		return responseDTO;
	}

	@Override
	public ResponseServiceDTO executeUpdateUserByIdService(Map<String, String> headers, RequestAddUserExampleModel body, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseServiceDTO executeUpdateTypeUserByIdService(Map<String, String> headers, int userType,
			int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseServiceDTO executeDeleteUserByIdService(Map<String, String> headers, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
