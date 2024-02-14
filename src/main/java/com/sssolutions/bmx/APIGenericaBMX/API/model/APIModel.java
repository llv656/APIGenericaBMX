package com.sssolutions.bmx.APIGenericaBMX.API.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class APIModel {
	
	private String folio;
	private String token;
	private int idApi;
	private int idEndpoint;
	private String fechaSolicitud;
	private String direccionIpCliente;
	private String servidor;
	
}
