package com.sssolutions.bmx.APIGenericaBMX.dto;

public class ValidationDTO {
	
	private boolean valid;
	private String[] message;
	
	public ValidationDTO() {}
	
	public ValidationDTO(boolean valid, String[] message) {
		this.valid = valid;
		this.message = message;
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

}
