package com.sssolutions.bmx.APIGenericaBMX.dto;

public class ResponseOKwhitDataDTO {
	
	private String folio;
	private String mensaje;
	private Object result;
	
	public ResponseOKwhitDataDTO() {}
	
	public ResponseOKwhitDataDTO(String folio, String mensaje, Object result) {
		this.folio = folio;
		this.mensaje = mensaje;
		this.result = result;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
