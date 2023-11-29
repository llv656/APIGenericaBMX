package com.sssolutions.bmx.APIGenericaBMX.API.model;

public class APIModel {
	
	private String folio;
	private String token;
	private int idApi;
	private int idEndpoint;
	private String fechaSolicitud;
	private String direccionIpCliente;
	private String servidor;
	
	public APIModel() {}
	
	public APIModel(String folio, String token, int idApi, int idEndpoint, String fechaSolicitud,
			String direccionIpCliente, String servidor) {
		this.folio = folio;
		this.token = token;
		this.idApi = idApi;
		this.idEndpoint = idEndpoint;
		this.fechaSolicitud = fechaSolicitud;
		this.direccionIpCliente = direccionIpCliente;
		this.servidor = servidor;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getIdApi() {
		return idApi;
	}

	public void setIdApi(int idApi) {
		this.idApi = idApi;
	}

	public int getIdEndpoint() {
		return idEndpoint;
	}

	public void setIdEndpoint(int idEndpoint) {
		this.idEndpoint = idEndpoint;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getDireccionIpCliente() {
		return direccionIpCliente;
	}

	public void setDireccionIpCliente(String direccionIpCliente) {
		this.direccionIpCliente = direccionIpCliente;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}
	
}
