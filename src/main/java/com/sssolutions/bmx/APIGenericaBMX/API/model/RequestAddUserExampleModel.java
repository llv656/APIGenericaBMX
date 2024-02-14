package com.sssolutions.bmx.APIGenericaBMX.API.model;

import java.time.LocalDate;

import org.apache.commons.lang.StringEscapeUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@NotNull
public class RequestAddUserExampleModel {

	@NotNull(message = "Falta campo: nombre")
	@Size(min = 2, max = 30, message = "La longitud del campo nombre es inválido")
	private String nombre;
	
	@NotNull(message = "Falta campo: apellido paterno")
	@Size(min = 2, max = 30, message = "La longitud del campo apellido paterno es inválido")
	@JsonProperty("apellido_paterno")
	private String apellidoPaterno;
	
	@JsonProperty("apellido_materno")
	private String apellidoMaterno;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@JsonProperty("fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@NotNull(message = "Falta campo: tipo usuario")
	@JsonProperty("id_tipo_usuario")
	private Integer idTipoUsuario;
	
	public void sanitizeFields() {
        this.nombre = StringEscapeUtils.escapeSql(this.nombre);
        this.apellidoPaterno = StringEscapeUtils.escapeSql(this.apellidoPaterno);
        this.apellidoMaterno = StringEscapeUtils.escapeSql(this.apellidoMaterno);
    }
}
