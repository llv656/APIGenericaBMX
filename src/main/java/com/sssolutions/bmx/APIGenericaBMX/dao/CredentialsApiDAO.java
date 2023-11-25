package com.sssolutions.bmx.APIGenericaBMX.dao;

import java.sql.CallableStatement;
import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sssolutions.bmx.APIGenericaBMX.dao.service.IDAOUtilsService;
import com.sssolutions.bmx.APIGenericaBMX.dao.service.IWebCredentialsDAOService;
import com.sssolutions.bmx.APIGenericaBMX.dto.DAOResponseDTO;
import com.sssolutions.bmx.APIGenericaBMX.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.model.WebAppCredentialsModel;

@Repository
public class CredentialsApiDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(CredentialsApiDAO.class);
	private DataSource dataSource;
	private IWebCredentialsDAOService repositoryService;
	private IDAOUtilsService daoUtilsService;
	private final String schemaName;
	private final String functionGetClientCredentials;

	@Autowired
	public CredentialsApiDAO (
		DataSource dataSource, 
		IWebCredentialsDAOService repositoryService, 
		IDAOUtilsService daoUtilsService
	) {
		this.dataSource = dataSource;
		this.repositoryService = repositoryService;
		this.daoUtilsService = daoUtilsService;
		
		this.schemaName = "sss";
		this.functionGetClientCredentials = "fn_obtener_credenciales_api_bd";
	}
	
	public DAOResponseDTO getDataSourceWebApp (WebAppCredentialsModel clientModel, APIModel propertiesRequest) {
		LOGGER.info("\t\tMethod: ".concat(new Object(){}.getClass().getEnclosingMethod().getName()));
		
		DAOResponseDTO response = new DAOResponseDTO();
		
		try (Connection connection = dataSource.getConnection()) {
			LOGGER.info("\t\tConstrucción de query: obtener credenciales API "
					.concat(String.valueOf(propertiesRequest.getIdApi())));
			String query = repositoryService.buildQueryGetBDCredentials(schemaName, functionGetClientCredentials
					,clientModel.getWebAppID(), propertiesRequest);
			
			LOGGER.info("\t\tInvocación de sentencia");
			CallableStatement procedure = connection.prepareCall(query);
			
			LOGGER.info("\t\tSe ejecuta DAO");
			procedure.execute();
			
			LOGGER.info("\t\tRecuperación de respuesta de ejecución");
			response = repositoryService.getBDCredentials(procedure.getResultSet(), clientModel.getWebAppKey());
			
			procedure.close();
			
		} catch (Exception ex) {
			response = daoUtilsService.getExceptionResponse(functionGetClientCredentials, ex);
		} 
		
		LOGGER.info("\t\tTermina ".concat(new Object(){}.getClass().getEnclosingMethod().getName()));
	
		return response;
	}

}
