package com.sssolutions.bmx.APIGenericaBMX.API.service;

import java.util.Map;

import com.sssolutions.bmx.APIGenericaBMX.API.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseDTO;

public interface ICredentialsService {
	
	ResponseDTO executeGetDataSourceWebApp(Map<String,String> headers, APIModel propertiesRequest);

}
