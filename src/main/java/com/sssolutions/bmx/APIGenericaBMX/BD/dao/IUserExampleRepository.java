package com.sssolutions.bmx.APIGenericaBMX.BD.dao;

import java.util.concurrent.CompletableFuture;

import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.dto.ResponseServiceDTO;
import com.sssolutions.bmx.dto.ResponseDaoDTO;

public interface IUserExampleRepository {

	ResponseDaoDTO addClient(RequestAddUserExampleModel userModel, CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO);
	ResponseDaoDTO getClient(Integer userId, CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO);

}
