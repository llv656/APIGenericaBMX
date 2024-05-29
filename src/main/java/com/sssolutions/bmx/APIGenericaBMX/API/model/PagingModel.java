package com.sssolutions.bmx.APIGenericaBMX.API.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagingModel {
	
	@JsonProperty("items_page")
	private int itemsPage;
	
	@JsonProperty("actual_page")
	private int actualPage;
	
	@JsonProperty("total_items")
	private int totalItems;
	
	@JsonProperty("total_pages")
	private int totalPages;

	private Object items;
}
