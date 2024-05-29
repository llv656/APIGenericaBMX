package com.sssolutions.bmx.APIGenericaBMX.API.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagingSettingsModel {

	private Integer pageSize;
	private Integer pageNumber;
	private String order;
	
}
