package com.sssolutions.bmx.APIGenericaBMX.API.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseGetUserExampleModel {

	@JsonProperty("id_usuario")
	private String idUsuario;
	private String nombre;
	@JsonProperty("apellido_paterno")
	private String apellidoPaterno;
	@JsonProperty("apellido_materno")
	private String apellidoMaterno;
	@JsonProperty("fecha_nacimiento")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate fechaNacimiento;
	@JsonProperty("id_tipo_usuario")
	private int idTipoUsuario;
	@JsonProperty("fecha_creacion")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate fechaCreacion;
	
	public ResponseGetUserExampleModel(String idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno,
			LocalDate fechaNacimiento, int idTipoUsuario, LocalDate fechaCreacion) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaNacimiento = fechaNacimiento;
		this.idTipoUsuario = idTipoUsuario;
		this.fechaCreacion = fechaCreacion;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
