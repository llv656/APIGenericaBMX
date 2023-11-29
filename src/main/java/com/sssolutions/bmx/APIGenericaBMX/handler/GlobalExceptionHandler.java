package com.sssolutions.bmx.APIGenericaBMX.handler;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sssolutions.bmx.APIGenericaBMX.API.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.utils.APIService;
import com.sssolutions.bmx.APIGenericaBMX.values.Messages;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);
	private APIService apiService;
	
	@Autowired
	public GlobalExceptionHandler(APIService apiService) {
		this.apiService = apiService;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exceptionHandler(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("**exceptionHandler");
		
		LOGGER.error(ex.getMessage());
		LOGGER.error(ex.toString());
		
		Map<String, String> serverErrorResponse = new HashMap<String, String>();
		APIModel apiModel = apiService.getpropertiesRequest(request.getRemoteAddr(), this.getClass().getEnclosingMethod().getName());
		
		serverErrorResponse.put("folio", apiModel.getFolio());
		serverErrorResponse.put("Error", Messages.SERVER_ERROR_001);
		
		return new ResponseEntity<Object>(serverErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
