package com.sssolutions.bmx.APIGenericaBMX.BD.dao;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseServiceDTO;
import com.sssolutions.bmx.RepositoryBMX.pojo.ResponseDaoDTO;

public interface IUserExampleRepository {

	ResponseDaoDTO addClient(RequestAddUserExampleModel userModel, CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO);
	ResponseDaoDTO getClient(Integer userId, Map<String, String> dbConnectionDetails);

}
