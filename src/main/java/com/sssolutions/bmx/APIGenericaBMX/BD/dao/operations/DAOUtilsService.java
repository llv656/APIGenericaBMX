package com.sssolutions.bmx.APIGenericaBMX.dao.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sssolutions.bmx.APIGenericaBMX.API.enums.GenericErrorsDAO;
import com.sssolutions.bmx.APIGenericaBMX.dao.service.IDAOUtilsService;
import com.sssolutions.bmx.APIGenericaBMX.dto.DAOResponseDTO;

@Service
public class DAOUtilsService implements IDAOUtilsService {
	private static final Logger LOGGER = LogManager.getLogger(DAOUtilsService.class);
	
	@Override
	public DAOResponseDTO getExceptionResponse (String programName, Exception ex) {
		LOGGER.info("\t\t\tMethod: ".concat(new Object(){}.getClass().getEnclosingMethod().getName()));
		
		DAOResponseDTO response = new DAOResponseDTO();
		String[] message = { "Error al ejecutar: ".concat(programName) };
		
		LOGGER.error("\t\t\t".concat(message[0]));
		LOGGER.error("\t\t\t".concat(ex.getMessage()));
		
		response.setValid(false);
		response.setGenericError(GenericErrorsDAO.connection);
		response.setMessage(message);

		return response;
	}
	
	@Override
	public DAOResponseDTO getSimpleResponseOperation (ResultSet resultSet) throws SQLException {
		LOGGER.info("\t\t\tMethod: ".concat(new Object(){}.getClass().getEnclosingMethod().getName()));
		
		DAOResponseDTO response = new DAOResponseDTO();
		
		while (resultSet.next()) {
			response.setValid(resultSet.getBoolean(1));
			if (!response.isValid()) {
				response.setGenericError(GenericErrorsDAO.execution);
			}
			response.setMessage(new String[] { resultSet.getString(2) }); 
		}
		
		LOGGER.info("\t\t\tTermina ".concat(new Object(){}.getClass().getEnclosingMethod().getName()));
		
		resultSet.close();
		return response;
	}
}
