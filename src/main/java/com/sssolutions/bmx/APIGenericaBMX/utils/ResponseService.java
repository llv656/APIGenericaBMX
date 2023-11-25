package com.sssolutions.bmx.APIGenericaBMX.utils;

import org.springframework.stereotype.Service;

import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseDeleteDTO;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseErrorDTO;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseOKwhitDataDTO;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseOkDTO;

@Service
public class ResponseService{
	
	public ResponseOkDTO buildResponseOK(String folio, String mensaje) {
		return new ResponseOkDTO(folio, mensaje);
	}
	
	public ResponseOKwhitDataDTO buildResponseOKwhitData(String folio, String mensaje, Object result) {
		return new ResponseOKwhitDataDTO(folio, mensaje, result);
	}
	
	public ResponseDeleteDTO buildResponseDelete(String folio, String mensaje, String[] detalles) {
		return new ResponseDeleteDTO(folio, mensaje, detalles);
	}
	
	public ResponseErrorDTO buildResponseError(String codigoError, String folio, String mensaje, String[] detalles) {
		return new ResponseErrorDTO(codigoError, folio, mensaje, detalles);
	}
	
}
