package com.sssolutions.bmx.APIGenericaBMX.BD.dao;

import java.util.concurrent.CompletableFuture;

import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.dto.ResponseServiceDTO;
import com.sssolutions.bmx.dto.ResponseDaoDTO;

/**
 * This interface represents the repository for managing user examples.
 * 
 * @author Lenin Leines Vite
 * @version 1.0.0
 */
public interface IUserExampleRepository {

	/**
	 * Adds a client user example to the repository.
	 *
	 * @param userModel                   The user model containing the client information.
	 * @param credentiaslAsyncResponseDAO The CompletableFuture for handling the response asynchronously.
	 * @return                            The response data transfer object.
	 */
	ResponseDaoDTO addClient(RequestAddUserExampleModel userModel, CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO);

	/**
	 * Retrieves a client user example from the repository.
	 *
	 * @param userId                      The ID of the client user.
	 * @param credentiaslAsyncResponseDAO The CompletableFuture for handling the response asynchronously.
	 * @return                            The response data transfer object.
	 */
	ResponseDaoDTO getClient(Integer userId, CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO);
	
	/**
	 * Validates the username of a client user example from the repository.
	 *
	 * @param credentiaslAsyncResponseDAO The CompletableFuture for handling the response asynchronously.
	 * @return                            The response data transfer object.
	 */
	ResponseDaoDTO validateUserExist(String username, CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO);

}
