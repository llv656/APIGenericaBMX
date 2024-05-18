package com.sssolutions.bmx.APIGenericaBMX.BD.dao;

import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
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
	 * @return                            The response data transfer object.
	 */
	ResponseDaoDTO addClient(RequestAddUserExampleModel userModel);

	/**
	 * Retrieves a client user example from the repository.
	 *
	 * @param userId                      The ID of the client user.
	 * @return                            The response data transfer object.
	 */
	ResponseDaoDTO getClient(Integer userId);
	
	/**
	 * Validates the username of a client user example from the repository.
	 *
	 * @return                            The response data transfer object.
	 */
	ResponseDaoDTO validateUserExist(String username);

}
