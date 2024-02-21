package com.sssolutions.bmx.APIGenericaBMX.API.controller.utils;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.sssolutions.bmx.APIGenericaBMX.API.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.config.PropertyConfig;
import com.sssolutions.bmx.APIGenericaBMX.values.Properties;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class APIService{
	private static final Logger LOGGER = LogManager.getLogger(APIService.class);
	
	private PropertyConfig property;
	
	@Async
	public CompletableFuture<APIModel> getpropertiesRequest (String IPAddrees, String endpoint) {
		
		APIModel apiModel = new APIModel();
		
		try {
			
			InetAddress addr = InetAddress.getLocalHost();
            CompletableFuture<String> randomSerieFuture = getRandomNumber();
            CompletableFuture<String> dateSerieFuture = getDateSerie();
            CompletableFuture<String> dateFuture = getDate();
            CompletableFuture<Integer> serviceIdFuture = getServiceId(endpoint);
            
            CompletableFuture<Void> allFutures = CompletableFuture.allOf(randomSerieFuture, dateSerieFuture, dateFuture, serviceIdFuture);
			
            allFutures.thenRun(() -> { 
                String randomSerie = randomSerieFuture.join(); 
                String dateSerie = dateSerieFuture.join(); 
                String date = dateFuture.join(); 
                int serviceId = serviceIdFuture.join();
                
                apiModel.setDireccionIpCliente(IPAddrees);
                apiModel.setFechaSolicitud(date);
                apiModel.setFolio("BMX".concat(dateSerie).concat(randomSerie));
                apiModel.setServidor(addr.getHostAddress());
                apiModel.setIdApi(property.getPropertyInteger(Properties.API));
                apiModel.setIdEndpoint(serviceId);
            });
		} catch (Exception e) {
			
			LOGGER.error(e.getMessage());
		}
		
        return CompletableFuture.completedFuture(apiModel);
		
	}
	
	/*{CHANGE_ARTEFACT}*/
	private CompletableFuture<Integer> getServiceId(String endpoint) {
		
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

		return CompletableFuture.completedFuture(servicioId);
		
	}
	
	private CompletableFuture<String> getRandomNumber() {
		String cadena = String.valueOf(Math.abs((UUID.fromString(UUID.randomUUID().toString()).hashCode())));
		cadena = cadena.substring(0, 3);
		return CompletableFuture.completedFuture(cadena);
	}
	
	private CompletableFuture<String> getDate() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		String date = dateFormat.format(LocalDateTime.now());
		return CompletableFuture.completedFuture(date);
	}
	
	private CompletableFuture<String> getDateSerie() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		String date = dateFormat.format(LocalDateTime.now());
		return CompletableFuture.completedFuture(date);
	}
	
}
