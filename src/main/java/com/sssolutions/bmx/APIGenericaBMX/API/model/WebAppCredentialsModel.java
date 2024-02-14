package com.sssolutions.bmx.APIGenericaBMX.API.model;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WebAppCredentialsModel {
	
	private UUID webAppID;
	private String webAppKey;
	
}
