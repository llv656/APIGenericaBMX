package com.sssolutions.bmx.APIGenericaBMX.API.service;

import java.util.Map;

import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseDTO;
import com.sssolutions.bmx.APIGenericaBMX.model.APIModel;

public interface ICredentialsService {
	
	ResponseDTO executeGetDataSourceWebApp(Map<String,String> headers, APIModel propertiesRequest);

}
