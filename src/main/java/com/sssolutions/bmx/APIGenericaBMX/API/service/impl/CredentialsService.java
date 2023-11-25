package com.sssolutions.bmx.APIGenericaBMX.API.service.impl;

import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sssolutions.bmx.APIGenericaBMX.API.enums.GenericErrorsDAO;
import com.sssolutions.bmx.APIGenericaBMX.API.service.ICredentialsService;
import com.sssolutions.bmx.APIGenericaBMX.dao.CredentialsApiDAO;
import com.sssolutions.bmx.APIGenericaBMX.dto.DAOResponseDTO;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseDTO;
import com.sssolutions.bmx.APIGenericaBMX.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.model.WebAppCredentialsModel;
import com.sssolutions.bmx.APIGenericaBMX.values.Messages;

@Service
public class CredentialsService implements ICredentialsService {
	
	private static final Logger LOGGER = LogManager.getLogger(CredentialsService.class);
	private CredentialsApiDAO credentialsApiDAO;
	
	@Autowired
	public CredentialsService(CredentialsApiDAO credentialsApiDAO) {
		this.credentialsApiDAO = credentialsApiDAO;
	}

	@Override
	public ResponseDTO executeGetDataSourceWebApp(Map<String, String> headers, APIModel propertiesRequest) {
		LOGGER.info("\t\tMethod:".concat(new Object(){}.getClass().getEnclosingMethod().getName()));
		
		WebAppCredentialsModel webAppCredentials = new WebAppCredentialsModel();
		ResponseDTO responseDTO = new ResponseDTO();
		
		LOGGER.info("\t\tDTO credenciales app web ");
		webAppCredentials.setWebAppID(StringEscapeUtils.escapeSql(headers.get("x_web_app_id")));
		webAppCredentials.setWebAppKey(StringEscapeUtils.escapeSql(headers.get("x_web_app_key")));
		
		LOGGER.info("\t\tRecuperación de usuario API BD");
		DAOResponseDTO responseDAO = credentialsApiDAO.getDataSourceWebApp(webAppCredentials, propertiesRequest);
		
		LOGGER.info("\t\tConstrucción de respuesta del servicio");
		if (responseDAO.isValid()) {
			responseDTO.setResultado(responseDAO.getResult());
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

}
