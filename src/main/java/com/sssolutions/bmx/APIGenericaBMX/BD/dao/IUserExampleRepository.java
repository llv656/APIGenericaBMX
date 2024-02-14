package com.sssolutions.bmx.APIGenericaBMX.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.APIGenericaBMX.dao.service.IDAOUtilsService;
import com.sssolutions.bmx.APIGenericaBMX.dao.service.IUserExampleDAOService;
import com.sssolutions.bmx.APIGenericaBMX.dto.DAOResponseDTO;
import com.sssolutions.bmx.RepositoryBMX.RepositoryBMX;

@Repository
public class AddUserExampleDAO {

	private static final Logger LOGGER = LogManager.getLogger(AddUserExampleDAO.class);
	private IDAOUtilsService daoUtilsService;
	private IUserExampleDAOService daoService;
	private final String schemaName;
	private final String storeProcedureInsert;

	@Autowired
	public AddUserExampleDAO(
		IDAOUtilsService daoUtilsService,
		IUserExampleDAOService daoService
	) {
		this.daoUtilsService = daoUtilsService;
		this.daoService = daoService;
		
		this.schemaName = "test_sss";
		this.storeProcedureInsert = "sp_registrar_usuario";
	}

	public DAOResponseDTO addClientRepository (RequestAddUserExampleModel userModel, Map<String, String> dbConnectionDetails) {
		LOGGER.info("\t\tMethod: ".concat(new Object(){}.getClass().getEnclosingMethod().getName()));
		
		RepositoryBMX repository = new RepositoryBMX();
		
		DAOResponseDTO responseDAO = new DAOResponseDTO();
		
		try {
			
			LOGGER.info("\t\tConstrucción de query de agregar usuario");
			String query = daoService.buildQueryAddUser(schemaName, storeProcedureInsert, userModel);
			
			LOGGER.info("\t\tSe obtiene repositorio Web");
			Map<String, Object> result = repository.getWebRepository(dbConnectionDetails, query);
			
			LOGGER.info("\t\tSe obtiene la conexión del repositorio Web");
			Connection connection_BMX = (Connection) result.get("connection");;
			
			LOGGER.info("\t\tSe obtiene invocación de sentencia del repositorio Web");
			CallableStatement procedure_BMX = (CallableStatement) result.get("procedure");
			
			LOGGER.info("\t\tSe ejecuta DAO");
			procedure_BMX.execute();
			
			LOGGER.info("\t\tRecuperación de respuesta de ejecución");
			responseDAO = daoUtilsService.getSimpleResponseOperation(procedure_BMX.getResultSet());
			
			LOGGER.info("\t\tSe cierra la conexión del repositorio Web");
			procedure_BMX.close();
			connection_BMX.close();
			
		} catch (Exception ex) {
			responseDAO = daoUtilsService.getExceptionResponse(storeProcedureInsert, ex);
		}
		
		LOGGER.info("\t\tTermina ".concat(new Object(){}.getClass().getEnclosingMethod().getName()));
	
		return responseDAO;
	}
}
