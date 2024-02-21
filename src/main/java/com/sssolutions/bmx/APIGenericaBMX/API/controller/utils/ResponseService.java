package com.sssolutions.bmx.APIGenericaBMX.API.controller.utils;

import org.springframework.stereotype.Component;

import com.sssolutions.bmx.dto.ResponseAPIDeleteDTO;
import com.sssolutions.bmx.dto.ResponseAPIErrorDTO;
import com.sssolutions.bmx.dto.ResponseAPIOkDTO;
import com.sssolutions.bmx.dto.ResponseAPIOkWhitDataDTO;

@Component
public class ResponseService{
	
	public ResponseAPIOkDTO buildResponseOK(String folio, String mensaje) {
		return new ResponseAPIOkDTO(folio, mensaje);
	}
	
	public <T> ResponseAPIOkWhitDataDTO<T> buildResponseOkWhitData(String folio, String mensaje, T result) {
		return new ResponseAPIOkWhitDataDTO<T>(folio, mensaje, result);
	}
	
	public ResponseAPIDeleteDTO buildResponseDelete(String folio, String mensaje, String[] detalles) {
		return new ResponseAPIDeleteDTO(folio, mensaje, detalles);
	}
	
	public ResponseAPIErrorDTO buildResponseError(String codigoError, String folio, String mensaje, String[] detalles) {
		return new ResponseAPIErrorDTO(codigoError, folio, mensaje, detalles);
	}
	
}
