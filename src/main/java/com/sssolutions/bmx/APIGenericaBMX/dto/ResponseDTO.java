package com.sssolutions.bmx.APIGenericaBMX.dto;

import org.springframework.http.HttpStatus;

public class ResponseDTO {

	private boolean exitoso;
	private String mensaje;
	private String[] detalles;
	private HttpStatus estatus;
	private Object resultado;
	
	public ResponseDTO() {}
	
	public ResponseDTO(boolean exitoso, String mensaje, HttpStatus estatus, Object resultado, String[] detalles) {
		this.exitoso = exitoso;
		this.mensaje = mensaje;
		this.estatus = estatus;
		this.resultado = resultado;
		this.detalles = detalles;
	}

	public boolean isExitoso() {
		return exitoso;
	}

	public void setExitoso(boolean exitoso) {
		this.exitoso = exitoso;
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

	public HttpStatus getEstatus() {
		return estatus;
	}

	public void setEstatus(HttpStatus estatus) {
		this.estatus = estatus;
	}

	public Object getResultado() {
		return resultado;
	}

	public void setResultado(Object resultado) {
		this.resultado = resultado;
	}
	
}
