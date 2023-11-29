package com.sssolutions.bmx.APIGenericaBMX.dao.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sssolutions.bmx.APIGenericaBMX.API.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.dto.DAOResponseDTO;

public interface IWebCredentialsDAOService {
	
	String buildQueryGetBDCredentials(String schemaName, String functionGetClientCredentials,
			String webAppID, APIModel propertiesRequest);
	
	DAOResponseDTO getBDCredentials (ResultSet resultSet, String webAppKey) throws SQLException;

}
