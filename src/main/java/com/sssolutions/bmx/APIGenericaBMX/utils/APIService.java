package com.sssolutions.bmx.APIGenericaBMX.utils;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sssolutions.bmx.APIGenericaBMX.config.PropertyConfig;
import com.sssolutions.bmx.APIGenericaBMX.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.values.Properties;

@Service
public class APIService{
	private static final Logger LOGGER = LogManager.getLogger(APIService.class);
	
	@Autowired
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
				case "addUserController":
					servicioId = property.getPropertyInteger(Properties.ENDPOINT_ADD_USER);
					break;
				case "getUsersController":
					servicioId = property.getPropertyInteger(Properties.ENDPOINT_GET_USERS);
					break;
				case "getUserController":
					servicioId = property.getPropertyInteger(Properties.ENDPOINT_GET_USER);
					break;
				case "updateUserController":
					servicioId = property.getPropertyInteger(Properties.ENDPOINT_UPDATE_USER);
					break;
				case "updateTypeUserController":
					servicioId = property.getPropertyInteger(Properties.ENDPOINT_UPDATE_TYPE_USER);
					break;
				case "deleteUserController":
					servicioId = property.getPropertyInteger(Properties.ENDPOINT_DELETE_USER);
					break;
			}
			
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
		String eventDate = "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		eventDate = simpleDateFormat.format(new Date());
		return eventDate;
	}
	
	private String getDateSerie() {
		String date = "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		date = simpleDateFormat.format(new Date());
		return date;
	}
	
}
