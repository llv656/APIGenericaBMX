package com.sssolutions.bmx.APIGenericaBMX.dto;

public class ResponseErrorDTO {
	
	private String codigoError;
	private String folio;
	private String mensaje;
	private String[] detalles;
	
	public ResponseErrorDTO() {}

	public ResponseErrorDTO(String codigoError, String folio, String mensaje, String[] detalles) {
		this.codigoError = codigoError;
		this.folio = folio;
		this.mensaje = mensaje;
		this.detalles = detalles;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
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
