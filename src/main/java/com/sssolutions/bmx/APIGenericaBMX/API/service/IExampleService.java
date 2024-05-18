package com.sssolutions.bmx.APIGenericaBMX.API.service;

import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.dto.ResponseServiceDTO;

/**
 * This interface represents the contract for the Example Service.
 * It provides methods to perform various operations related to users.
 * 
 * @author Lenin Leines Vite
 * @version 1.0.0
 */
public interface IExampleService {

	/**
	 * Executes the add user service asynchronously.
	 * 
	 * @param body The RequestAddUserExampleModel object containing the user details.
	 * @return The ResponseServiceDTO object representing the response from the service.
	 */
	public ResponseServiceDTO executeAddUserService(RequestAddUserExampleModel body);
	
	/**
	 * Executes the get users service asynchronously.
	 * 
	 * @return The ResponseServiceDTO object representing the response from the service.
	 */
	public ResponseServiceDTO executeGetUsersService();
	
	/**
	 * Executes the get user by ID service asynchronously.
	 * 
	 * @param id The ID of the user to retrieve.
	 * @return The ResponseServiceDTO object representing the response from the service.
	 */
	public ResponseServiceDTO executeGetUserByIdService(Integer id);

	/**
	 * Executes the update user by ID service asynchronously.
	 * 
	 * @param body The RequestAddUserExampleModel object containing the updated user details.
	 * @param id The ID of the user to update.
	 * @return The ResponseServiceDTO object representing the response from the service.
	 */
	public ResponseServiceDTO executeUpdateUserByIdService(RequestAddUserExampleModel body, int id);
	
	/**
	 * Executes the update type user by ID service asynchronously.
	 * 
	 * @param userType The updated type of the user.
	 * @param id The ID of the user to update.
	 * @return The ResponseServiceDTO object representing the response from the service.
	 */
	public ResponseServiceDTO executeUpdateTypeUserByIdService(int userType, int id);
	
	/**
	 * Executes the delete user by ID service asynchronously.
	 * 
	 * @param id The ID of the user to delete.
	 * @return The ResponseServiceDTO object representing the response from the service.
	 */
	public ResponseServiceDTO executeDeleteUserByIdService(int id);
}
