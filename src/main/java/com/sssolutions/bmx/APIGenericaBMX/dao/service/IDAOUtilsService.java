package com.sssolutions.bmx.APIGenericaBMX.dao.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sssolutions.bmx.APIGenericaBMX.dto.DAOResponseDTO;

public interface IDAOUtilsService {

	DAOResponseDTO getExceptionResponse (String programName, Exception ex);
	
	DAOResponseDTO getSimpleResponseOperation (ResultSet resultSet) throws SQLException;
	
}
