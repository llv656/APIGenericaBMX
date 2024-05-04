package com.sssolutions.bmx.APIGenericaBMX.API.service;

import java.util.concurrent.CompletableFuture;

import com.sssolutions.bmx.APIGenericaBMX.API.model.APIModel;
import com.sssolutions.bmx.dto.ResponseServiceDTO;

/**
 * This interface represents the service for handling credentials.
 * 
 * @author Lenin Leines Vite
 * @version 1.0.0
 */
public interface ICredentialsService {
	
	/**
	 * Executes a GET request to the data source web app.
	 * 
	 * @param webAppId - The WebAppId header to be included in the request.
	 * @param webAppKey - The WebAppKey header to be included in the request
	 * @param propertiesRequest - The CompletableFuture containing the APIModel for the request.
	 * @return A CompletableFuture containing the ResponseServiceDTO for the request.
	 */
	CompletableFuture<ResponseServiceDTO> executeGetDataSourceWebApp(
			String webAppId,
			String webAppKey,
			CompletableFuture<APIModel> propertiesRequest
	);

}
