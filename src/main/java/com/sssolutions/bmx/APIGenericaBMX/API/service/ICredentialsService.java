package com.sssolutions.bmx.APIGenericaBMX.API.service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.sssolutions.bmx.APIGenericaBMX.API.model.APIModel;
import com.sssolutions.bmx.dto.ResponseServiceDTO;

public interface ICredentialsService {
	
	CompletableFuture<ResponseServiceDTO> executeGetDataSourceWebApp(Map<String,String> headers, APIModel propertiesRequest);

}
