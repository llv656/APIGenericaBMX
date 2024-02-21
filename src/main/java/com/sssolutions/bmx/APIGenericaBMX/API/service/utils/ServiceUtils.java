package com.sssolutions.bmx.APIGenericaBMX.API.service.utils;

import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.sssolutions.bmx.dto.ResponseServiceDTO;
import com.sssolutions.bmx.APIGenericaBMX.values.Messages;
import com.sssolutions.bmx.enums.DAOStatus;
import com.sssolutions.bmx.functionalInterface.TriFunction;
import com.sssolutions.bmx.dto.ResponseDaoDTO;

@Component
public class ServiceUtils {
	
	private static final Logger LOGGER = LogManager.getLogger(ServiceUtils.class);
	
	public final TriFunction<String, HttpStatus, Supplier<ResponseDaoDTO>, ResponseServiceDTO> executeService = (m, hs, c) -> {
		LOGGER.info("\t\tMethod:".concat(m));
		
		ResponseServiceDTO responseDTO = new ResponseServiceDTO();
		ResponseDaoDTO responseDAO = c.get();

		LOGGER.info("\t\tConstrucción de respuesta del servicio");
		if (responseDAO.isValid()) {
			responseDTO.setMessage(Messages.OK_001);
			responseDTO.setResult(responseDAO.getResult());
			responseDTO.setHttpStatus(hs);
		} else if (!responseDAO.isValid() && responseDAO.getDaoStatus() == DAOStatus.err_execution) {
			responseDTO.setMessage(Messages.ERROR_001);
			responseDTO.setDetails(responseDAO.getMessage());
			responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST);
		} else if (!responseDAO.isValid() && responseDAO.getDaoStatus() == DAOStatus.err_permissions) {
			responseDTO.setMessage(Messages.ERROR_CONNECTION_BD_001);
			responseDTO.setDetails(responseDAO.getMessage());
			responseDTO.setHttpStatus(HttpStatus.UNAUTHORIZED);
		} else if (!responseDAO.isValid() && responseDAO.getDaoStatus() == DAOStatus.err_connection) {
			responseDTO.setMessage(Messages.ERROR_CONNECTION_BD_001);
			responseDTO.setDetails(responseDAO.getMessage());
			responseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		responseDTO.setValid(responseDAO.isValid());
		
		return responseDTO;
	};
}
