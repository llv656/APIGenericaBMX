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

/**
 * This class represents the API service that retrieves properties request.
 * 
 * @author Lenin Leines Vite
 * @version 1.0.0
 */
@Component
@AllArgsConstructor
public class APIUtils {
	private static final Logger LOGGER = LogManager.getLogger(APIUtils.class);

	private PropertyConfig property;

	/**
	 * Retrieves the properties request from the specified IP address and endpoint asynchronously.
	 *
	 * @param IPAddrees The IP address from which the request is made.
	 * @param endpoint The endpoint for the request.
	 * @return A CompletableFuture containing the APIModel object representing the properties request.
	 */
	@Async
	public CompletableFuture<APIModel> getpropertiesRequest(String IPAddrees, String endpoint) {

		APIModel apiModel = new APIModel();

		try {

			InetAddress addr = InetAddress.getLocalHost();
			apiModel.setDireccionIpCliente(IPAddrees);
			apiModel.setFechaSolicitud(getDate());
			apiModel.setFolio("BMX".concat(getDateSerie()).concat(getRandomNumber()));
			apiModel.setServidor(addr.getHostAddress());
			apiModel.setIdApi(property.getPropertyInteger(Properties.API));
			apiModel.setIdEndpoint(getServiceId(endpoint));

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return CompletableFuture.completedFuture(apiModel);

	}

	/**
	 * Retrieves the service ID based on the provided endpoint.
	 *
	 * @param endpoint The endpoint for the request.
	 * @return The service ID associated with the endpoint.
	 */
	/*{CHANGE_ARTEFACT}*/
	private Integer getServiceId(String endpoint) {

		int servicioId = 0;

		try {
			if (endpoint.matches("GET-/BMX/apigenerica/user_example/.*"))
				servicioId = property.getPropertyInteger(Properties.ENDPOINT_GET_USER);
			else if (endpoint.matches("PUT-/BMX/apigenerica/user_example/.*"))
				servicioId = property.getPropertyInteger(Properties.ENDPOINT_UPDATE_USER);
			else if (endpoint.matches("DELETE-/BMX/apigenerica/user_example/.*"))
				servicioId = property.getPropertyInteger(Properties.ENDPOINT_DELETE_USER);
			else if (endpoint.matches("DELETE-/BMX/apigenerica/user_example/tipo_usuario/.*"))
				servicioId = property.getPropertyInteger(Properties.ENDPOINT_UPDATE_TYPE_USER);
			else
				switch (endpoint) {
					case "POST-/BMX/apigenerica/user_example" -> servicioId = property.getPropertyInteger(Properties.ENDPOINT_ADD_USER);
					case "GET-/BMX/apigenerica/user_example" -> servicioId = property.getPropertyInteger(Properties.ENDPOINT_GET_USERS);
				};
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return servicioId;

	}

	private String getRandomNumber() {
		String cadena = String.valueOf(Math.abs((UUID.fromString(UUID.randomUUID().toString()).hashCode())));
		cadena = cadena.substring(0, 3);
		return cadena;
	}

	private String getDate() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		String date = dateFormat.format(LocalDateTime.now());
		return date;
	}

	private String getDateSerie() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		String date = dateFormat.format(LocalDateTime.now());
		return date;
	}

}
