package com.sssolutions.bmx.APIGenericaBMX.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseServiceDTO {

	private boolean valid;
	private String message;
	private String[] details;
	private HttpStatus httpStatus;
	private Object result;
	
}
