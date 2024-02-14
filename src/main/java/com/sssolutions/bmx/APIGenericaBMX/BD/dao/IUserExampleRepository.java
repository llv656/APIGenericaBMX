package com.sssolutions.bmx.APIGenericaBMX.BD.dao;

import java.util.Map;

import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.RepositoryBMX.pojo.ResponseDaoDTO;

public interface IUserExampleRepository {

	ResponseDaoDTO addClient(RequestAddUserExampleModel userModel, Map<String, String> dbConnectionDetails);
	ResponseDaoDTO getClient(Integer userId, Map<String, String> dbConnectionDetails);

}
