package com.sssolutions.bmx.APIGenericaBMX.API.controller.utils;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sssolutions.bmx.APIGenericaBMX.API.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.BD.dao.IUserExampleRepository;
import com.sssolutions.bmx.APIGenericaBMX.config.PropertyConfig;
import com.sssolutions.bmx.APIGenericaBMX.values.Properties;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class APIService{
	private static final Logger LOGGER = LogManager.getLogger(APIService.class);
	
	private PropertyConfig property;
	
	public APIModel getpropertiesRequest (String IPAddrees, String endpoint) {
		
		APIModel apiModel = new APIModel();
		
		try {
			
			InetAddress addr = InetAddress.getLocalHost();
			String serieRandom = getRandomNumber();
			
			apiModel.setDireccionIpCliente(IPAddrees);
			apiModel.setFechaSolicitud(getDate());
			apiModel.setFolio("BMX".concat(getDateSerie()).concat(serieRandom));
			apiModel.setServidor(addr.getHostAddress());
			apiModel.setIdApi(property.getPropertyInteger(Properties.API));
			apiModel.setIdEndpoint(getServiceId(endpoint));
			
		} catch (Exception e) {
			
			LOGGER.error(e.getMessage());
		}
		
		return apiModel;
		
	}
	
	/*{CHANGE_ARTEFACT}*/
	private int getServiceId(String endpoint) {
		
		int servicioId = 0;
		
		try {
			switch (endpoint) {
				case "addUserController" -> servicioId = property.getPropertyInteger(Properties.ENDPOINT_ADD_USER);
				case "getUsersController" -> servicioId = property.getPropertyInteger(Properties.ENDPOINT_GET_USERS);
				case "getUserController" -> servicioId = property.getPropertyInteger(Properties.ENDPOINT_GET_USER);
				case "updateUserController" -> servicioId = property.getPropertyInteger(Properties.ENDPOINT_UPDATE_USER);
				case "updateTypeUserController" -> servicioId = property.getPropertyInteger(Properties.ENDPOINT_UPDATE_TYPE_USER);
				case "deleteUserController" -> servicioId = property.getPropertyInteger(Properties.ENDPOINT_DELETE_USER);
			};
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return servicioId;
		
	}
	
	private String getRandomNumber() {
		String cadena = String.valueOf(Math.abs((UUID.fromString(UUID.randomUUID().toString()).hashCode())));
		return cadena.substring(0, 3);
	}
	
	private String getDate() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		return dateFormat.format(LocalDateTime.now());
	}
	
	private String getDateSerie() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		return dateFormat.format(LocalDateTime.now());
	}
	
}
