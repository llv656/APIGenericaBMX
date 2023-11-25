package com.sssolutions.bmx.APIGenericaBMX.API.service.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sssolutions.bmx.APIGenericaBMX.API.enums.GenericErrorsDAO;
import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.APIGenericaBMX.API.service.IExampleService;
import com.sssolutions.bmx.APIGenericaBMX.dao.AddUserExampleDAO;
import com.sssolutions.bmx.APIGenericaBMX.dto.DAOResponseDTO;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseDTO;
import com.sssolutions.bmx.APIGenericaBMX.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.values.Messages;

@Service
public class ExampleServiceImpl implements IExampleService{
	
	private static final Logger LOGGER = LogManager.getLogger(ExampleServiceImpl.class);
	private AddUserExampleDAO addUserExampleDAO;

	@Autowired
	public ExampleServiceImpl(
		AddUserExampleDAO addUserExampleDAO
	) {
		this.addUserExampleDAO = addUserExampleDAO;
	}

	@Override
	public ResponseDTO executeAddUserService(Map<String, String> responseCredentialsDAO, APIModel propertiesRequest,
			RequestAddUserExampleModel body) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		LOGGER.info("\t\tMethod:".concat(method));
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		/* Agregar validaciones y sanitización de campos */
		
		LOGGER.info("\t\tRegistro de usuario");
		DAOResponseDTO responseDAO = addUserExampleDAO.addClientRepository(body, responseCredentialsDAO);
		
		LOGGER.info("\t\tConstrucción de respuesta del servicio");
		if (responseDAO.isValid()) {
			responseDTO.setMensaje(Messages.OK_001);
			responseDTO.setEstatus(HttpStatus.CREATED);
		} else if (!responseDAO.isValid() && responseDAO.getGenericError() == GenericErrorsDAO.execution) {
			responseDTO.setMensaje(Messages.ERROR_001);
			responseDTO.setDetalles(responseDAO.getMessage());
			responseDTO.setEstatus(HttpStatus.BAD_REQUEST);
		} else if (!responseDAO.isValid() && responseDAO.getGenericError() == GenericErrorsDAO.connection) {
			responseDTO.setMensaje(Messages.ERROR_CONNECTION_BD_001);
			responseDTO.setDetalles(responseDAO.getMessage());
			responseDTO.setEstatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		responseDTO.setExitoso(responseDAO.isValid());
		
		return responseDTO;
	}

	@Override
	public ResponseDTO executeGetUsersService(Map<String, String> headers, APIModel propertiesRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO executeGetUserByIdService(Map<String, String> headers, APIModel propertiesRequest, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO executeUpdateUserByIdService(Map<String, String> headers, APIModel propertiesRequest,
			RequestAddUserExampleModel body, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO executeUpdateTypeUserByIdService(Map<String, String> headers, APIModel propertiesRequest, int userType,
			int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO executeDeleteUserByIdService(Map<String, String> headers, APIModel propertiesRequest, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
