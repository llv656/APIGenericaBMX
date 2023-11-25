package com.sssolutions.bmx.APIGenericaBMX.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.APIGenericaBMX.dao.service.IUserExampleDAOService;
import com.sssolutions.bmx.APIGenericaBMX.dao.utils.SQLUtilsService;

@Service
public class UserExampleDAOService implements IUserExampleDAOService {

	@Autowired
	private SQLUtilsService sqlUtils;
	
	@Override
	public String buildQueryAddUser(String schemaName, String storeProcedureInsert,
			RequestAddUserExampleModel userModel) {

		String buidQueryProcedure = "CALL "
									.concat(schemaName).concat(".").concat(storeProcedureInsert)
									.concat("(")
									.concat(sqlUtils.getStringOrNullValue(userModel.getNombre()))
									.concat(",")
									.concat(sqlUtils.getStringOrNullValue(userModel.getApellidoPaterno()))
									.concat(",")
									.concat(sqlUtils.getStringOrNullValue(userModel.getApellidoMaterno()))
									.concat(",")
									.concat(sqlUtils.getLocalDateOrNullValue(userModel.getFechaNacimiento()))
									.concat(",")
									.concat(sqlUtils.setCastSmallInt(userModel.getIdTipoUsuario()))
									.concat(",")
									.concat("null")
									.concat(",")
									.concat("null")
									.concat(")");
		
		return buidQueryProcedure;
	}

}
