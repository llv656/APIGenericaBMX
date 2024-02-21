package com.sssolutions.bmx.APIGenericaBMX.handler;

import java.time.format.DateTimeParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sssolutions.bmx.APIGenericaBMX.API.controller.utils.APIService;
import com.sssolutions.bmx.APIGenericaBMX.API.controller.utils.ResponseService;
import com.sssolutions.bmx.APIGenericaBMX.API.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.values.Messages;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);
	private APIService apiService;
	private ResponseService responseService;
	
	public GlobalExceptionHandler(APIService apiService, ResponseService responseService) {
		this.apiService = apiService;
		this.responseService = responseService;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exceptionHandler(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("**exceptionHandler");
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		
		APIModel apiModel = apiService.getpropertiesRequest(request.getRemoteAddr(), method);
		
		if (ex.getCause() instanceof InvalidFormatException &&
				ex.getCause().getCause() instanceof DateTimeParseException) {
	        Object responseErrorTDateTime = responseService.buildResponseError("400", apiModel.getFolio(), Messages.ERROR_FORMAT_DATE_TIME, null);
	        return new ResponseEntity<>(responseErrorTDateTime, HttpStatus.BAD_REQUEST);
		}
		
		if(ex instanceof HttpMessageNotReadableException) {
			Object responseErrorFormat = responseService.buildResponseError("400", apiModel.getFolio(), Messages.ERROR_FORMAT_JSON, null);
	        return new ResponseEntity<>(responseErrorFormat, HttpStatus.BAD_REQUEST);
		}

		Object responseError = responseService.buildResponseError("500", apiModel.getFolio(), Messages.SERVER_ERROR_001, null);
		return new ResponseEntity<Object>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
