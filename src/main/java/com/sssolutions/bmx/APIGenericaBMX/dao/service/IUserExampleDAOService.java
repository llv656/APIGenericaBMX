package com.sssolutions.bmx.APIGenericaBMX.dao.service;

import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;

public interface IUserExampleDAOService {

	String buildQueryAddUser(String schemaName, String storeProcedureInsert, RequestAddUserExampleModel userModel);
	
}
