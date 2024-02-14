package com.sssolutions.bmx.APIGenericaBMX.dao.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sssolutions.bmx.APIGenericaBMX.API.enums.GenericErrorsDAO;
import com.sssolutions.bmx.APIGenericaBMX.API.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.config.PropertyConfig;
import com.sssolutions.bmx.APIGenericaBMX.dao.service.IWebCredentialsDAOService;
import com.sssolutions.bmx.APIGenericaBMX.dao.utils.SQLUtilsService;
import com.sssolutions.bmx.APIGenericaBMX.dto.DAOResponseDTO;
import com.sssolutions.bmx.APIGenericaBMX.values.Properties;
import com.sssolutions.bmx.CryptoBMX.CryptoBMX;

@Service
public class WebCredentialsDAOService implements IWebCredentialsDAOService {
	private static final Logger LOGGER = LogManager.getLogger(WebCredentialsDAOService.class);
	
	@Autowired
	private PropertyConfig property;
	@Autowired
	private SQLUtilsService daoUtils;
	
	public String buildQueryGetBDCredentials(String schemaName, String functionGetClientCredentials,
			String webAppID, APIModel propertiesRequest) {
		LOGGER.info("\t\t\tMethod: ".concat(new Object(){}.getClass().getEnclosingMethod().getName()));

		String buidQueryFunction = "SELECT * FROM "
							.concat(schemaName).concat(".").concat(functionGetClientCredentials)
							.concat("(")
							.concat(daoUtils.setCastUUID(webAppID))
							.concat(",")
							.concat(daoUtils.setCastSmallInt(propertiesRequest.getIdApi()))
							.concat(",")
							.concat(daoUtils.setCastSmallInt(propertiesRequest.getIdEndpoint()))
							.concat(")");

		LOGGER.info("\t\t\tTermina ".concat(new Object(){}.getClass().getEnclosingMethod().getName()));

		return buidQueryFunction;

	}
	
	public DAOResponseDTO getBDCredentials (ResultSet resultSet, String webAppKey) throws SQLException {
		LOGGER.info("\t\t\tMethod: ".concat(new Object(){}.getClass().getEnclosingMethod().getName()));
		
		DAOResponseDTO responseDAO = new DAOResponseDTO();
		Map<String, String> response = new HashMap<>();
		
		CryptoBMX cryptoBMX =  new CryptoBMX();
		try {
			resultSet.next();
			response.put("api.db.driver", property.getPropertyString(Properties.JDBC_DRIVER));
			response.put("api.db.serverAddress", property.getPropertyString(Properties.JDBC_URL));
			response.put("api.db.database", resultSet.getString(2));
			response.put("api.db.schema", resultSet.getString(3));
			response.put("api.db.usr", resultSet.getString(4));
			response.put("api.db.passwd", resultSet.getString(5));
			
			response = cryptoBMX.getCredentialsBD_BMX(response, webAppKey, property.getPropertyString(Properties.CRYPTO_KEY_SEPARATOR));
			responseDAO.setValid(true);
			responseDAO.setResult(response);
		} catch (Exception e) {
			responseDAO.setValid(false);
			responseDAO.setGenericError(GenericErrorsDAO.execution);
		}
		
		LOGGER.info("\t\t\tTermina ".concat(new Object(){}.getClass().getEnclosingMethod().getName()));
		
		resultSet.close();
		return responseDAO;
	}
	
}
