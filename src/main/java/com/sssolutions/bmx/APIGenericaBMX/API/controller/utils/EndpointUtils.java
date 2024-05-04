package com.sssolutions.bmx.APIGenericaBMX.API.controller.utils;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
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

import com.sssolutions.bmx.dto.ResponseServiceDTO;
import com.sssolutions.bmx.APIGenericaBMX.API.aspects.PreProcessingSetupAspect;
import com.sssolutions.bmx.APIGenericaBMX.API.model.APIModel;

import lombok.AllArgsConstructor;

/**
 * This class provides utility methods for executing API endpoints.
 * 
 * @author Lenin Leines Vite
 * @version 1.0.0
 */
@Component
@AllArgsConstructor
public class EndpointUtils {
	
	private static final Logger LOGGER = LogManager.getLogger(EndpointUtils.class);
	private ResponseService responseService;

	/**
	 * Represents an implementation of the Endpoint interface.
	 * Executes the endpoint request and returns the response.
	 *
	 * @param headers   The headers for the request.
	 * @param method    The HTTP method for the request.
	 * @param adrress   The address of the endpoint.
	 * @param callback  The callback function to process the response.
	 * @return          The ResponseEntity containing the response and HTTP status.
	 */
	public final BiFunction<
		String,
		Function<CompletableFuture<ResponseServiceDTO>, ResponseServiceDTO>,
		ResponseEntity<Object>
	> executeEndpoint = (method, callback) -> {
		LOGGER.info("**Empieza solicitud ".concat(method));
		CompletableFuture<APIModel> propertiesRequest = PreProcessingSetupAspect.asyncRequestProperties.get();
		CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponse = PreProcessingSetupAspect.asyncCredentials.get();
		
		ResponseServiceDTO responseDTO = callback.apply(credentiaslAsyncResponse);
		
		LOGGER.info("\tConstrucción de respuesta");
		Object response = responseDTO.isValid()
				? !method.startsWith("get") 
					? responseService.buildResponseOK(
							propertiesRequest.join().getFolio(), responseDTO.getMessage())
					: responseService.buildResponseOkWhitData(
							propertiesRequest.join().getFolio(), responseDTO.getMessage(), responseDTO.getResult())
				: responseService.buildResponseError(
						responseDTO.getHttpStatus().toString(), propertiesRequest.join().getFolio(), responseDTO.getMessage(), responseDTO.getDetails());
		
		LOGGER.info("**Termina solicitud ".concat(method));
		return new ResponseEntity<Object>(response,responseDTO.getHttpStatus());
	};
	
	/**
	 * Transforms the given Errors object into a ResponseServiceDTO object.
	 * 
	 * @param e the Errors object containing the error information
	 * @return the ResponseServiceDTO object with transformed error information
	 */
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
