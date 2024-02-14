package com.sssolutions.bmx.APIGenericaBMX.API.service;

import java.util.Map;

import com.sssolutions.bmx.APIGenericaBMX.API.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseServiceDTO;

public interface ICredentialsService {
	
	ResponseServiceDTO executeGetDataSourceWebApp(Map<String,String> headers, APIModel propertiesRequest);

}
