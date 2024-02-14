package com.sssolutions.bmx.APIGenericaBMX.dto;

public class ResponseOkDTO {
	
	private String folio;
	private String mensaje;
	
	public ResponseOkDTO() {}

	public ResponseOkDTO(String folio, String mensaje) {
		this.folio = folio;
		this.mensaje = mensaje;
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
	
}
