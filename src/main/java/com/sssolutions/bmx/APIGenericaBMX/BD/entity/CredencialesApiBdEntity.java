package com.sssolutions.bmx.APIGenericaBMX.BD.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CredencialesApiBdEntity {
	
	@Id
	@Column(name ="ci_negocio_digital_id")
	private Integer digitalBusinessId;
	@Column(name ="cs_nombre_bd")
	private String bdName;
	@Column(name ="cs_esquema_bd")
	private String schemaBd;
	@Column(name ="cs_usr_bd_encrypted")
	private String usrBd;
	@Column(name ="cs_pass_bd_encrypted")
	private String passBd;
}
