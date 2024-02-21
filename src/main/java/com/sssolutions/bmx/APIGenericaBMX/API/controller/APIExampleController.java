package com.sssolutions.bmx.APIGenericaBMX.API.controller;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
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

import com.sssolutions.bmx.APIGenericaBMX.API.controller.utils.EndpointUtils;
import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.APIGenericaBMX.API.service.IExampleService;
import com.sssolutions.bmx.dto.ResponseServiceDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("user_example")
@AllArgsConstructor
public class APIExampleController {

	private static final Logger LOGGER = LogManager.getLogger(APIExampleController.class);
	private HttpServletRequest request;
	private IExampleService exampleService;
	private EndpointUtils endpointUtils;

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object addUserController(
			@RequestHeader Map<String, String> headers, 
			@RequestBody @Valid RequestAddUserExampleModel body, 
			Errors errors
	) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		
		Function<CompletableFuture<ResponseServiceDTO>, ResponseServiceDTO> callback = futureCredentials -> {
			ResponseServiceDTO responseDTO = new ResponseServiceDTO();
			if (errors.hasErrors()) {
				responseDTO = endpointUtils.transformErrorsAtServiceResponse.apply(errors);
			} else {
				LOGGER.info("\tEmpieza servicio agregar usuario");
				responseDTO = exampleService.executeAddUserService(futureCredentials, body);
			}
			return responseDTO;
		};
		
		return endpointUtils.executeEndpoint.apply(headers, method, request.getRemoteAddr(), callback);	
	}
	
	@GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object getUsersController(
			@RequestHeader Map<String, String> headers
	) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		
		Function<CompletableFuture<ResponseServiceDTO>, ResponseServiceDTO> callback = futureCredentials -> {
			LOGGER.info("\tEmpieza servicio recuperar usuario");
			ResponseServiceDTO responseDTO = new ResponseServiceDTO();
			responseDTO = exampleService.executeGetUsersService(futureCredentials);
			return responseDTO;
		};
		return endpointUtils.executeEndpoint.apply(headers, method, request.getRemoteAddr(), callback);
	}
	
	@GetMapping(value = {"/{userId}"},consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object getUserController(
			@RequestHeader Map<String, String> headers, 
			@PathVariable Integer userId
	) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		
		Function<CompletableFuture<ResponseServiceDTO>, ResponseServiceDTO> callback = futureCredentials -> {
			LOGGER.info("\tEmpieza servicio agregar usuario");
			ResponseServiceDTO responseDTO = new ResponseServiceDTO();
			responseDTO = exampleService.executeGetUserByIdService(futureCredentials, userId);
			return responseDTO;
		};
		
		return endpointUtils.executeEndpoint.apply(headers, method, request.getRemoteAddr(), callback);
	}
	
	@PutMapping(value = {"/{idUser}"},consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object updateUserController(
			@RequestHeader Map<String, String> headers, 
			@RequestBody RequestAddUserExampleModel body, 
			@PathVariable int idUser
	) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		
		Function<CompletableFuture<ResponseServiceDTO>, ResponseServiceDTO> callback = futureCredentials -> {
			LOGGER.info("\tEmpieza servicio actualizar usuario");
			ResponseServiceDTO responseDTO = new ResponseServiceDTO();
			responseDTO = exampleService.executeUpdateUserByIdService(futureCredentials, body, idUser);
			return responseDTO;
		};
		
		return endpointUtils.executeEndpoint.apply(headers, method, request.getRemoteAddr(), callback);
	}
	
	@PutMapping(value = {"/tipo_usuario/{idUser}"},consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object updateTypeUserController(
			@RequestHeader Map<String, String> headers, 
			@RequestBody RequestAddUserExampleModel body, 
			@PathVariable int idUser, 
			@RequestParam int typeUser
	) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		
		Function<CompletableFuture<ResponseServiceDTO>, ResponseServiceDTO> callback = futureCredentials -> {
			LOGGER.info("\tEmpieza servicio actualizar tipo usuario");
			ResponseServiceDTO responseDTO = new ResponseServiceDTO();
			responseDTO = exampleService.executeUpdateTypeUserByIdService(futureCredentials, typeUser, idUser);
			return responseDTO;
		};	
		
		return endpointUtils.executeEndpoint.apply(headers, method, request.getRemoteAddr(), callback);
	}
	
	@DeleteMapping(value = {"/{idUser}"},consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object deleteUserController(
			@RequestHeader Map<String, String> headers, 
			@PathVariable int idUser
	) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		
		Function<CompletableFuture<ResponseServiceDTO>, ResponseServiceDTO> callback = futureCredentials -> {
			LOGGER.info("\tEmpieza servicio eliminar usuario");
			ResponseServiceDTO responseDTO = new ResponseServiceDTO();
			responseDTO = exampleService.executeDeleteUserByIdService(futureCredentials, idUser);
			return responseDTO;
		};
		
		return endpointUtils.executeEndpoint.apply(headers, method, request.getRemoteAddr(), callback);
	}
}