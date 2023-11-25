package com.sssolutions.bmx.APIGenericaBMX.API.controller;

import java.util.Map;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.APIGenericaBMX.API.service.ICredentialsService;
import com.sssolutions.bmx.APIGenericaBMX.API.service.IExampleService;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseDTO;
import com.sssolutions.bmx.APIGenericaBMX.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.utils.APIService;
import com.sssolutions.bmx.APIGenericaBMX.utils.ResponseService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user_example")
public class APIExampleController {

	private static final Logger LOGGER = LogManager.getLogger(APIExampleController.class);
	private ResponseService responseService;
	private APIService apiService;
	private HttpServletRequest request;
	private ICredentialsService credentialsService;
	private IExampleService exampleService;
	private Function<ResponseDTO, Map<String, String>> transformResultAtDataSource;
	
	@Autowired
    public APIExampleController(
            ResponseService responseService,
            APIService apiService,
            HttpServletRequest request,
            ICredentialsService credentialsService,
            IExampleService exampleService
    ) {
        this.responseService = responseService;
        this.apiService = apiService;
        this.request = request;
        this.credentialsService = credentialsService;
        this.exampleService = exampleService;

        this.transformResultAtDataSource = a -> (Map<String, String>) a.getResultado();
    }

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object addUserController(@RequestHeader Map<String, String> headers, 
			@RequestBody RequestAddUserExampleModel body) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		LOGGER.info("**Empieza solicitud ".concat(method));
		
		LOGGER.info("\tConfiguración de propiedades de solicitud");
		APIModel propertiesRequest = apiService.getpropertiesRequest(request.getRemoteAddr(), method);
		
		LOGGER.info("\tEmpieza servicio recuperar credenciales BD");
		ResponseDTO responseDTO = credentialsService.executeGetDataSourceWebApp(headers, propertiesRequest);
		
		if (responseDTO.isExitoso()) {
			LOGGER.info("\tEmpieza servicio agregar usuario");
			responseDTO = exampleService.executeAddUserService(this.transformResultAtDataSource.apply(responseDTO), propertiesRequest, body);
		}
	
		LOGGER.info("\tConstrucción de respuesta");
		Object response = responseDTO.isExitoso()
				? responseService.buildResponseOK(
						propertiesRequest.getFolio(), responseDTO.getMensaje())
				: responseService.buildResponseError(
						responseDTO.getEstatus().toString(), propertiesRequest.getFolio(), responseDTO.getMensaje(), responseDTO.getDetalles());
		
		LOGGER.info("**Termina solicitud ".concat(method));
		
		return new ResponseEntity<Object>(response,responseDTO.getEstatus());	
	}
	
	@GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object getUsersController(@RequestHeader Map<String, String> headers) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		LOGGER.info("**Empieza solicitud ".concat(method));
		
		LOGGER.info("\tConfiguración de propiedades de solicitud");
		APIModel propertiesRequest = apiService.getpropertiesRequest(request.getRemoteAddr(), method);
		
		LOGGER.info("\tEmpieza servicio recuperar credenciales BD");
		ResponseDTO responseDTO = credentialsService.executeGetDataSourceWebApp(headers, propertiesRequest);
		
		if (responseDTO.isExitoso()) {
			LOGGER.info("\tEmpieza servicio recuperar usuario");
			responseDTO = exampleService.executeGetUsersService(this.transformResultAtDataSource.apply(responseDTO), propertiesRequest);
		}
	
		LOGGER.info("\tConstrucción de respuesta");
		Object response = responseDTO.isExitoso()
				? responseService.buildResponseOKwhitData(
						propertiesRequest.getFolio(), responseDTO.getMensaje(), responseDTO.getResultado())
				: responseService.buildResponseError(
						responseDTO.getEstatus().toString(), propertiesRequest.getFolio(), responseDTO.getMensaje(), responseDTO.getDetalles());
		
		LOGGER.info("**Termina solicitud ".concat(method));
		
		return new ResponseEntity<Object>(response,responseDTO.getEstatus());	
	}
	
	@GetMapping(value = {"/{idUser}"},consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object getUserController(@RequestHeader Map<String, String> headers, @PathVariable int idUser) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		LOGGER.info("**Empieza solicitud ".concat(method));
		
		LOGGER.info("\tConfiguración de propiedades de solicitud");
		APIModel propertiesRequest = apiService.getpropertiesRequest(request.getRemoteAddr(), method);
		
		LOGGER.info("\tEmpieza servicio recuperar credenciales BD");
		ResponseDTO responseDTO = credentialsService.executeGetDataSourceWebApp(headers, propertiesRequest);
		
		if (responseDTO.isExitoso()) {
			LOGGER.info("\tEmpieza servicio obteber usuario");
			responseDTO = exampleService.executeGetUserByIdService(this.transformResultAtDataSource.apply(responseDTO), propertiesRequest, idUser);
		}
		
		LOGGER.info("\tConstrucción de respuesta");
		Object response = responseDTO.isExitoso()
				? responseService.buildResponseOKwhitData(
						propertiesRequest.getFolio(), responseDTO.getMensaje(), responseDTO.getResultado())
				: responseService.buildResponseError(
						responseDTO.getEstatus().toString(), propertiesRequest.getFolio(), responseDTO.getMensaje(), responseDTO.getDetalles());
		
		LOGGER.info("**Termina solicitud ".concat(method));
		
		return new ResponseEntity<Object>(response,responseDTO.getEstatus());	
	}
	
	@PutMapping(value = {"/{idUser}"},consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object updateUserController(@RequestHeader Map<String, String> headers, 
			@RequestBody RequestAddUserExampleModel body, @PathVariable int idUser) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		LOGGER.info("**Empieza solicitud ".concat(method));
		
		LOGGER.info("\tConfiguración de propiedades de solicitud");
		APIModel propertiesRequest = apiService.getpropertiesRequest(request.getRemoteAddr(), method);
		
		LOGGER.info("\tEmpieza servicio recuperar credenciales BD");
		ResponseDTO responseDTO = credentialsService.executeGetDataSourceWebApp(headers, propertiesRequest);
		
		if (responseDTO.isExitoso()) {
			LOGGER.info("\tEmpieza servicio actualizar usuario");
			responseDTO = exampleService.executeUpdateUserByIdService(this.transformResultAtDataSource.apply(responseDTO), propertiesRequest, body, idUser);
		}
		
		LOGGER.info("\tConstrucción de respuesta");
		Object response = responseDTO.isExitoso()
				? responseService.buildResponseOKwhitData(
						propertiesRequest.getFolio(), responseDTO.getMensaje(), responseDTO.getResultado())
				: responseService.buildResponseError(
						responseDTO.getEstatus().toString(), propertiesRequest.getFolio(), responseDTO.getMensaje(), responseDTO.getDetalles());
		
		LOGGER.info("**Termina solicitud ".concat(method));
		
		return new ResponseEntity<Object>(response,responseDTO.getEstatus());	
	}
	
	@PutMapping(value = {"/tipo_usuario/{idUser}"},consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object updateTypeUserController(@RequestHeader Map<String, String> headers, 
			@RequestBody RequestAddUserExampleModel body, @PathVariable int idUser, @RequestParam int typeUser) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		LOGGER.info("**Empieza solicitud ".concat(method));
		
		LOGGER.info("\tConfiguración de propiedades de solicitud");
		APIModel propertiesRequest = apiService.getpropertiesRequest(request.getRemoteAddr(), method);
		
		LOGGER.info("\tEmpieza servicio recuperar credenciales BD");
		ResponseDTO responseDTO = credentialsService.executeGetDataSourceWebApp(headers, propertiesRequest);
		
		if (responseDTO.isExitoso()) {
			LOGGER.info("\tEmpieza servicio actualizar tipo usuario");
			responseDTO = exampleService.executeUpdateTypeUserByIdService(this.transformResultAtDataSource.apply(responseDTO), propertiesRequest, typeUser, idUser);
		}
		LOGGER.info("\tConstrucción de respuesta");
		Object response = responseDTO.isExitoso()
				? responseService.buildResponseOK(
						propertiesRequest.getFolio(), responseDTO.getMensaje())
				: responseService.buildResponseError(
						responseDTO.getEstatus().toString(), propertiesRequest.getFolio(), responseDTO.getMensaje(), responseDTO.getDetalles());
		
		LOGGER.info("**Termina solicitud ".concat(method));
		
		return new ResponseEntity<Object>(response,responseDTO.getEstatus());	
	}
	
	@DeleteMapping(value = {"/{idUser}"},consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object deleteUserController(@RequestHeader Map<String, String> headers, @PathVariable int idUser) {
		String method = new Object(){}.getClass().getEnclosingMethod().getName();
		LOGGER.info("**Empieza solicitud ".concat(method));
		
		LOGGER.info("\tConfiguración de propiedades de solicitud");
		APIModel propertiesRequest = apiService.getpropertiesRequest(request.getRemoteAddr(), method);
		
		LOGGER.info("\tEmpieza servicio recuperar credenciales BD");
		ResponseDTO responseDTO = credentialsService.executeGetDataSourceWebApp(headers, propertiesRequest);
		
		if (responseDTO.isExitoso()) {
			LOGGER.info("\tEmpieza servicio eliminar usuario");
			responseDTO = exampleService.executeDeleteUserByIdService(this.transformResultAtDataSource.apply(responseDTO), propertiesRequest, idUser);
		}
		
		LOGGER.info("\tConstrucción de respuesta");
		Object response = responseDTO.isExitoso()
				? responseService.buildResponseDelete(
						propertiesRequest.getFolio(), responseDTO.getMensaje(), responseDTO.getDetalles())
				: responseService.buildResponseError(
						responseDTO.getEstatus().toString(), propertiesRequest.getFolio(), responseDTO.getMensaje(), responseDTO.getDetalles());
		
		LOGGER.info("**Termina solicitud ".concat(method));
		
		return new ResponseEntity<Object>(response,responseDTO.getEstatus());	
	}
	
}
