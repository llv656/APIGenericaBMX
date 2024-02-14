package com.sssolutions.bmx.APIGenericaBMX.BD.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FnUsuarioEntity {
	
	@JsonProperty("usuario_id")
	private Integer usuarioId;
	private String nombreUsuario;
	@JsonProperty("apellido_paterno")
	private String apellidoPaterno;
	@JsonProperty("apellido_materno")
	private String apellidoMaterno;
	@JsonProperty("fecha_nacimiento")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate fechaNacimiento;
	@JsonProperty("tipo_usuario_id")
	private Integer tipoUsuarioId;
	@JsonProperty("estatus_id")
	private Integer estatusId;
	@JsonProperty("fecha_creacion")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDateTime fechaCreacion;
}
