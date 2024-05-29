package com.sssolutions.bmx.APIGenericaBMX.API.service.utils;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * This interface represents the utility for management of request client QueryParams.
 * It provides methods to perform various operations related to query parameter consumption.
 * 
 * @author Lenin Leines Vite
 * @version 1.0.0
 */
public interface QueryParamsUtils {

	/**
	 * Get String Value of Query parameters.
	 *
	 * @param p		The value.
	 * @param l		The maximum length allowed for the value.
	 * @return		The sanitized value.
	 */
	BiFunction<String, Integer, String> getStringValue = (p, l) -> p == null || p.length() > l ? null : StringEscapeUtils.escapeSql(p);
	
	/**
	 * Get Integer Value of Query parameters.
	 *
	 * @param p		The value.
	 * @param l		The maximum digit length allowed for the value.
	 * @return		The validated value.
	 */
	BiFunction<String, Integer, String> getIntegerValue = (p, l) -> p != null && (p.isBlank() || !p.matches("^(\\d{1,".concat(String.valueOf(l)).concat("})*$"))) ? null : StringEscapeUtils.escapeSql(p);
	
	/**
	 * Get Boolean Value of Query parameters.
	 *
	 * @param p		The value.
	 * @return		The validated value.
	 */
	Function<String, Boolean> getBooleanValue = p -> p != null && (p.isEmpty() || p.isBlank() || !p.matches("(?i)^(true|false)$")) ? false : p.equalsIgnoreCase("true");
	
	/**
	 * Get Date pattern Value of Query parameters.
	 *
	 * @param d		The date pattern value.
	 * @return		The validated value.
	 */
	Function<String, String> getDatePatternValue = (d -> {
		String datePattern = "%-%-%";
		
		if (d != null && !d.isEmpty() && !d.isBlank()) {
			String[] dateParts = d.split("-");
			
			String day = dateParts[0];
			String month = dateParts[1];
			String year = dateParts[2];
			
			datePattern = (year.equals("*") || !year.matches("^(\\d{0,4})*$") ? "" : year).concat("%")
					.concat("-%").concat(month.equals("*") || !month.matches("^(\\d{0,2})*$") ? "" : month).concat("%")
					.concat("-%").concat(day.equals("*") || !day.matches("^(\\d{0,2})*$") ? "" : day);
		}
		
		return datePattern;
	});
	
}
