package com.sssolutions.bmx.APIGenericaBMX.dto;

public class ResponseDeleteDTO {

	private String folio;
	private String mensaje;
	private String[] detalles;
	
	public ResponseDeleteDTO() {}

	public ResponseDeleteDTO(String folio, String mensaje, String[] detalles) {
		this.folio = folio;
		this.mensaje = mensaje;
		this.detalles = detalles;
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
	
	public String[] getDetalles() {
		return detalles;
	}

	public void setDetalles(String[] detalles) {
		this.detalles = detalles;
	}
	
}
