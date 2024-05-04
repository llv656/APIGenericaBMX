package com.sssolutions.bmx.APIGenericaBMX.API.controller.utils;

import org.springframework.stereotype.Component;

import com.sssolutions.bmx.dto.ResponseAPIDeleteDTO;
import com.sssolutions.bmx.dto.ResponseAPIErrorDTO;
import com.sssolutions.bmx.dto.ResponseAPIOkDTO;
import com.sssolutions.bmx.dto.ResponseAPIOkWhitDataDTO;

/**
 * The ResponseService class provides methods to build different types of API responses.
 * 
 * @author Lenin Leines Vite
 * @version 1.0.0
 */
@Component
public class ResponseService {
	
	/**
	 * Builds a response object for a successful API request without data.
	 * 
	 * @param folio The folio of the response.
	 * @param mensaje The message of the response.
	 * @return The ResponseAPIOkDTO object representing the response.
	 */
	public ResponseAPIOkDTO buildResponseOK(String folio, String mensaje) {
		return new ResponseAPIOkDTO(folio, mensaje);
	}
	
	/**
	 * Builds a response object for a successful API request with data.
	 * 
	 * @param folio The folio of the response.
	 * @param mensaje The message of the response.
	 * @param result The data to be included in the response.
	 * @return The ResponseAPIOkWhitDataDTO object representing the response.
	 */
	public <T> ResponseAPIOkWhitDataDTO<T> buildResponseOkWhitData(String folio, String mensaje, T result) {
		return new ResponseAPIOkWhitDataDTO<T>(folio, mensaje, result);
	}
	
	/**
	 * Builds a response object for a delete API request.
	 * 
	 * @param folio The folio of the response.
	 * @param mensaje The message of the response.
	 * @param detalles The details of the response.
	 * @return The ResponseAPIDeleteDTO object representing the response.
	 */
	public ResponseAPIDeleteDTO buildResponseDelete(String folio, String mensaje, String[] detalles) {
		return new ResponseAPIDeleteDTO(folio, mensaje, detalles);
	}
	
	/**
	 * Builds a response object for an error in the API request.
	 * 
	 * @param codigoError The error code of the response.
	 * @param folio The folio of the response.
	 * @param mensaje The message of the response.
	 * @param detalles The details of the response.
	 * @return The ResponseAPIErrorDTO object representing the response.
	 */
	public ResponseAPIErrorDTO buildResponseError(String codigoError, String folio, String mensaje, String[] detalles) {
		return new ResponseAPIErrorDTO(codigoError, folio, mensaje, detalles);
	}
	
}
