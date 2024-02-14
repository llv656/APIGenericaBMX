package com.sssolutions.bmx.APIGenericaBMX.utils;

import org.springframework.stereotype.Component;

import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseAPIDeleteDTO;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseAPIErrorDTO;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseAPIOkWhitDataDTO;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseAPIOkDTO;

@Component
public class ResponseService{
	
	public ResponseAPIOkDTO buildResponseOK(String folio, String mensaje) {
		return new ResponseAPIOkDTO(folio, mensaje);
	}
	
	public ResponseAPIOkWhitDataDTO buildResponseOkWhitData(String folio, String mensaje, Object result) {
		return new ResponseAPIOkWhitDataDTO(folio, mensaje, result);
	}
	
	public ResponseAPIDeleteDTO buildResponseDelete(String folio, String mensaje, String[] detalles) {
		return new ResponseAPIDeleteDTO(folio, mensaje, detalles);
	}
	
	public ResponseAPIErrorDTO buildResponseError(String codigoError, String folio, String mensaje, String[] detalles) {
		return new ResponseAPIErrorDTO(codigoError, folio, mensaje, detalles);
	}
	
}
