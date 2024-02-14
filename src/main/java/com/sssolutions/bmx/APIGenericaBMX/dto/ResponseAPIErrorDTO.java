package com.sssolutions.bmx.APIGenericaBMX.dto;

public record ResponseAPIErrorDTO (
		String errorCode,
		String folio,
		String message,
		String[] details
		){}
