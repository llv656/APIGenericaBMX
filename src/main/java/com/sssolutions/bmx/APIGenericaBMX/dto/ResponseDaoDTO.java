package com.sssolutions.bmx.APIGenericaBMX.dto;

import com.sssolutions.bmx.APIGenericaBMX.API.enums.GenericErrorsDAO;

public class DAOResponseDTO {
	
	private boolean valid;
	private String[] message;
	private GenericErrorsDAO genericError;
	private Object result;
	
	public DAOResponseDTO() {}

	public DAOResponseDTO(boolean valid, String[] message, GenericErrorsDAO genericError, Object result) {
		super();
		this.valid = valid;
		this.message = message;
		this.genericError = genericError;
		this.result = result;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String[] getMessage() {
		return message;
	}

	public void setMessage(String[] message) {
		this.message = message;
	}

	public GenericErrorsDAO getGenericError() {
		return genericError;
	}

	public void setGenericError(GenericErrorsDAO genericError) {
		this.genericError = genericError;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
