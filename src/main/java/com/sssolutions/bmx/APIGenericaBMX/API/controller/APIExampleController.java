package com.sssolutions.bmx.APIGenericaBMX.API.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sssolutions.bmx.APIGenericaBMX.API.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.APIGenericaBMX.API.service.ICredentialsService;
import com.sssolutions.bmx.APIGenericaBMX.API.service.IExampleService;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseServiceDTO;
import com.sssolutions.bmx.APIGenericaBMX.utils.APIService;
import com.sssolutions.bmx.APIGenericaBMX.utils.UtilsService;
import com.sssolutions.bmx.APIGenericaBMX.utils.ResponseService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("user_example")
@AllArgsConstructor
public class APIExampleController {

	private static final Logger LOGGER = LogManager.getLogger(APIExampleController.class);
	private ResponseService responseService;
	private APIService apiService;
	private HttpServletRequest request;
	private ICredentialsService credentialsService;
	private IExampleService exampleService;
	private UtilsService utilsService;

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object addUserController(
			@RequestHeader Map<String, String> headers, 
			@RequestBody @Valid RequestAddUserExampleModel body, 
			Errors errors
	) {
		
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		LOGGER.info("**Empieza solicitud ".concat(method));
		
		ResponseServiceDTO responseDTO = new ResponseServiceDTO();
		
		LOGGER.info("\tConfiguración de propiedades de solicitud");
		APIModel propertiesRequest = apiService.getpropertiesRequest(request.getRemoteAddr(), method);
		
		if (errors.hasErrors()) {
			responseDTO = utilsService.transformErrorsAtResponseService.apply(errors);
		} else {
			LOGGER.info("\tEmpieza servicio de recuperar credenciales BD");
			responseDTO = credentialsService.executeGetDataSourceWebApp(headers, propertiesRequest);
			
			if (responseDTO.isValid()) {
				LOGGER.info("\tEmpieza servicio agregar usuario");
				responseDTO = exampleService.executeAddUserService(utilsService.transformResponseAtDataSourceMap.apply(responseDTO), body);
			}
		}
	
		LOGGER.info("\tConstrucción de respuesta");
		Object response = responseDTO.isValid()
				? responseService.buildResponseOK(
						propertiesRequest.getFolio(), responseDTO.getMessage())
				: responseService.buildResponseError(
						responseDTO.getHttpStatus().toString(), propertiesRequest.getFolio(), responseDTO.getMessage(), responseDTO.getDetails());
		
		LOGGER.info("**Termina solicitud ".concat(method));
		
		return new ResponseEntity<Object>(response,responseDTO.getHttpStatus());	
	}
	
	@GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object getUsersController(@RequestHeader Map<String, String> headers) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		LOGGER.info("**Empieza solicitud ".concat(method));
		
		LOGGER.info("\tConfiguración de propiedades de solicitud");
		APIModel propertiesRequest = apiService.getpropertiesRequest(request.getRemoteAddr(), method);
		
		LOGGER.info("\tEmpieza servicio recuperar credenciales BD");
		ResponseServiceDTO responseDTO = credentialsService.executeGetDataSourceWebApp(headers, propertiesRequest);
		
		if (responseDTO.isValid()) {
			LOGGER.info("\tEmpieza servicio recuperar usuario");
			responseDTO = exampleService.executeGetUsersService(utilsService.transformResponseAtDataSourceMap.apply(responseDTO));
		}
	
		LOGGER.info("\tConstrucción de respuesta");
		Object response = responseDTO.isValid()
				? responseService.buildResponseOkWhitData(
						propertiesRequest.getFolio(), responseDTO.getMessage(), responseDTO.getResult())
				: responseService.buildResponseError(
						responseDTO.getHttpStatus().toString(), propertiesRequest.getFolio(), responseDTO.getMessage(), responseDTO.getDetails());
		
		LOGGER.info("**Termina solicitud ".concat(method));
		
		return new ResponseEntity<Object>(response,responseDTO.getHttpStatus());	
	}
	
	@GetMapping(value = {"/{userId}"},consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object getUserController(
			@RequestHeader Map<String, String> headers, 
			@PathVariable Integer userId
			) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		LOGGER.info("**Empieza solicitud ".concat(method));
		
		ResponseServiceDTO responseDTO = new ResponseServiceDTO();
		
		LOGGER.info("\tConfiguración de propiedades de solicitud");
		APIModel propertiesRequest = apiService.getpropertiesRequest(request.getRemoteAddr(), method);
		
		LOGGER.info("\tEmpieza servicio de recuperar credenciales BD");
		responseDTO = credentialsService.executeGetDataSourceWebApp(headers, propertiesRequest);
		
		if (responseDTO.isValid()) {
			LOGGER.info("\tEmpieza servicio agregar usuario");
			responseDTO = exampleService.executeGetUserByIdService(utilsService.transformResponseAtDataSourceMap.apply(responseDTO), userId);
		}
	
		LOGGER.info("\tConstrucción de respuesta");
		Object response = responseDTO.isValid()
				? responseService.buildResponseOkWhitData(
						propertiesRequest.getFolio(), responseDTO.getMessage(), responseDTO.getResult())
				: responseService.buildResponseError(
						responseDTO.getHttpStatus().toString(), propertiesRequest.getFolio(), responseDTO.getMessage(), responseDTO.getDetails());
		
		LOGGER.info("**Termina solicitud ".concat(method));
		
		return new ResponseEntity<Object>(response,responseDTO.getHttpStatus());	
	}
	
	@PutMapping(value = {"/{idUser}"},consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object updateUserController(@RequestHeader Map<String, String> headers, 
			@RequestBody RequestAddUserExampleModel body, @PathVariable int idUser) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		LOGGER.info("**Empieza solicitud ".concat(method));
		
		LOGGER.info("\tConfiguración de propiedades de solicitud");
		APIModel propertiesRequest = apiService.getpropertiesRequest(request.getRemoteAddr(), method);
		
		LOGGER.info("\tEmpieza servicio recuperar credenciales BD");
		ResponseServiceDTO responseDTO = credentialsService.executeGetDataSourceWebApp(headers, propertiesRequest);
		
		if (responseDTO.isValid()) {
			LOGGER.info("\tEmpieza servicio actualizar usuario");
			responseDTO = exampleService.executeUpdateUserByIdService(utilsService.transformResponseAtDataSourceMap.apply(responseDTO), body, idUser);
		}
		
		LOGGER.info("\tConstrucción de respuesta");
		Object response = responseDTO.isValid()
				? responseService.buildResponseOkWhitData(
						propertiesRequest.getFolio(), responseDTO.getMessage(), responseDTO.getResult())
				: responseService.buildResponseError(
						responseDTO.getHttpStatus().toString(), propertiesRequest.getFolio(), responseDTO.getMessage(), responseDTO.getDetails());
		
		LOGGER.info("**Termina solicitud ".concat(method));
		
		return new ResponseEntity<Object>(response,responseDTO.getHttpStatus());	
	}
	
	@PutMapping(value = {"/tipo_usuario/{idUser}"},consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object updateTypeUserController(@RequestHeader Map<String, String> headers, 
			@RequestBody RequestAddUserExampleModel body, @PathVariable int idUser, @RequestParam int typeUser) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		LOGGER.info("**Empieza solicitud ".concat(method));
		
		LOGGER.info("\tConfiguración de propiedades de solicitud");
		APIModel propertiesRequest = apiService.getpropertiesRequest(request.getRemoteAddr(), method);
		
		LOGGER.info("\tEmpieza servicio recuperar credenciales BD");
		ResponseServiceDTO responseDTO = credentialsService.executeGetDataSourceWebApp(headers, propertiesRequest);
		
		if (responseDTO.isValid()) {
			LOGGER.info("\tEmpieza servicio actualizar tipo usuario");
			responseDTO = exampleService.executeUpdateTypeUserByIdService(utilsService.transformResponseAtDataSourceMap.apply(responseDTO), typeUser, idUser);
		}
		LOGGER.info("\tConstrucción de respuesta");
		Object response = responseDTO.isValid()
				? responseService.buildResponseOK(
						propertiesRequest.getFolio(), responseDTO.getMessage())
				: responseService.buildResponseError(
						responseDTO.getHttpStatus().toString(), propertiesRequest.getFolio(), responseDTO.getMessage(), responseDTO.getDetails());
		
		LOGGER.info("**Termina solicitud ".concat(method));
		
		return new ResponseEntity<Object>(response,responseDTO.getHttpStatus());	
	}
	
	@DeleteMapping(value = {"/{idUser}"},consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object deleteUserController(@RequestHeader Map<String, String> headers, @PathVariable int idUser) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		LOGGER.info("**Empieza solicitud ".concat(method));
		
		LOGGER.info("\tConfiguración de propiedades de solicitud");
		APIModel propertiesRequest = apiService.getpropertiesRequest(request.getRemoteAddr(), method);
		
		LOGGER.info("\tEmpieza servicio recuperar credenciales BD");
		ResponseServiceDTO responseDTO = credentialsService.executeGetDataSourceWebApp(headers, propertiesRequest);
		
		if (responseDTO.isValid()) {
			LOGGER.info("\tEmpieza servicio eliminar usuario");
			responseDTO = exampleService.executeDeleteUserByIdService(utilsService.transformResponseAtDataSourceMap.apply(responseDTO), idUser);
		}
		
		LOGGER.info("\tConstrucción de respuesta");
		Object response = responseDTO.isValid()
				? responseService.buildResponseDelete(
						propertiesRequest.getFolio(), responseDTO.getMessage(), responseDTO.getDetails())
				: responseService.buildResponseError(
						responseDTO.getHttpStatus().toString(), propertiesRequest.getFolio(), responseDTO.getMessage(), responseDTO.getDetails());
		
		LOGGER.info("**Termina solicitud ".concat(method));
		
		return new ResponseEntity<Object>(response,responseDTO.getHttpStatus());	
	}
	
}
