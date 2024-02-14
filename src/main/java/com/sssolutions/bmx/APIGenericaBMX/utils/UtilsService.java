package com.sssolutions.bmx.APIGenericaBMX.utils;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseServiceDTO;

@Component
public class UtilsService {
	
	public final Function<ResponseServiceDTO, Map<String, String>> transformResponseAtDataSourceMap = e -> (Map<String, String>) e.getResult();
	
	public final Function<Errors, ResponseServiceDTO> transformErrorsAtResponseService = e -> {
		ResponseServiceDTO responseDTO = new ResponseServiceDTO();
    	String camposError = e.getFieldErrors()
				.stream()
				.map(FieldError::getField)
				.collect(Collectors.joining(", "));
		
		String[] mensajesError = e.getAllErrors()
				.stream()
				.map(ObjectError::getDefaultMessage)
				.collect(Collectors.toList())
				.toArray(new String[e.getAllErrors().size()]);
		
		responseDTO.setValid(false);
		responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST);
		responseDTO.setMessage("Error en los campos: ".concat(camposError));
		responseDTO.setDetails(mensajesError);
		return responseDTO;
	};
}
