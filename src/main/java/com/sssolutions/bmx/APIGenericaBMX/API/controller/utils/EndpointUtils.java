package com.sssolutions.bmx.APIGenericaBMX.API.controller.utils;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.sssolutions.bmx.APIGenericaBMX.API.service.ICredentialsService;
import com.sssolutions.bmx.dto.ResponseServiceDTO;
import com.sssolutions.bmx.functionalInterface.QuadFunction;
import com.sssolutions.bmx.APIGenericaBMX.API.model.APIModel;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EndpointUtils {
	
	private static final Logger LOGGER = LogManager.getLogger(EndpointUtils.class);
	private ResponseService responseService;
	private APIService apiService;
	private ICredentialsService credentialsService;

	public final QuadFunction<
		Map<String, String>,
		String,
		String,
		Function<CompletableFuture<ResponseServiceDTO>, ResponseServiceDTO>,
		ResponseEntity<Object>
	> executeEndpoint = (headers, method, adrress, callback) -> {
		LOGGER.info("**Empieza solicitud ".concat(method));
		
		LOGGER.info("\tConfiguración de propiedades de solicitud");
		APIModel propertiesRequest = apiService.getpropertiesRequest(adrress, method);
		
		LOGGER.info("\tEmpieza servicio de recuperar credenciales BD");
		CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponse = credentialsService.executeGetDataSourceWebApp(headers, propertiesRequest);
		
		ResponseServiceDTO responseDTO = callback.apply(credentiaslAsyncResponse);
		
		LOGGER.info("\tConstrucción de respuesta");
		Object response = responseDTO.isValid()
				? !method.startsWith("get") 
					? responseService.buildResponseOK(
							propertiesRequest.getFolio(), responseDTO.getMessage())
					: responseService.buildResponseOkWhitData(
							propertiesRequest.getFolio(), responseDTO.getMessage(), responseDTO.getResult())
				: responseService.buildResponseError(
						responseDTO.getHttpStatus().toString(), propertiesRequest.getFolio(), responseDTO.getMessage(), responseDTO.getDetails());
		
		LOGGER.info("**Termina solicitud ".concat(method));
		return new ResponseEntity<Object>(response,responseDTO.getHttpStatus());
	};
	
	public final Function<Errors, ResponseServiceDTO> transformErrorsAtServiceResponse = e -> {
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
