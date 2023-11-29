package com.sssolutions.bmx.APIGenericaBMX.API.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebAppCredentialsModel {
	
	@JsonProperty("web_app_id")
	private String webAppID;
	@JsonProperty("web_app_key")
	private String webAppKey;

	public WebAppCredentialsModel() {}
	
	public WebAppCredentialsModel(String webAppID, String webAppKey) {
		super();
		this.webAppID = webAppID;
		this.webAppKey = webAppKey;
	}

	public String getWebAppID() {
		return webAppID;
	}

	public void setWebAppID(String webAppID) {
		this.webAppID = webAppID;
	}

	public String getWebAppKey() {
		return webAppKey;
	}

	public void setWebAppKey(String webAppKey) {
		this.webAppKey = webAppKey;
	}
	
}
