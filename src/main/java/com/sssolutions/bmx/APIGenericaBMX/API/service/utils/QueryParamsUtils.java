package com.sssolutions.bmx.APIGenericaBMX.API.service.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringEscapeUtils;

import com.sssolutions.bmx.functionalInterface.TriFunction;

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
	 * @param p		The values array.
	 * @param l		The maximum length allowed for the value.
	 * @return		The sanitized value.
	 */
	static BiFunction<String[], Integer, String> getStringValue = (p, l) -> p == null || p[0] == null || p[0].isBlank() || p[0].length() > l ? null : StringEscapeUtils.escapeSql(p[0]);
	
	/**
	 * Get String Value of Query parameters.
	 *
	 * @param p		The values array.
	 * @param l		The maximum length allowed for the value.
	 * @return		The sanitized value.
	 */
	static BiFunction<String[], Integer, String> getIntValueToString = (p, l) -> p == null || p[0] == null || p[0] != null && (p[0].isBlank() || !p[0].matches("^(\\d{1,".concat(String.valueOf(l)).concat("})*$"))) ? null : StringEscapeUtils.escapeSql(p[0]);
	
	/**
	 * Get Integer Value of Query parameters.
	 *
	 * @param p		The values array.
	 * @param l		The maximum digit length allowed for the value.
	 * @return		The validated value.
	 */
	static BiFunction<String[], Integer, Integer> getIntegerValue = (p, l) -> p == null || p[0] == null || p[0] != null && (p[0].isBlank() || !p[0].matches("^(\\d{1,".concat(String.valueOf(l)).concat("})*$"))) ? null : Integer.valueOf(p[0]);

	/**
	 * Get Float Value of Query parameters.
	 *
	 * @param p		The values array
	 * @param l		The maximum digit length allowed for decimal rounding.
	 * @return		The validated value.
	 */
	static BiFunction<String[], Integer, Float> getFloatValue = (p, l) -> p == null || p[0] == null || p[0] != null && (p[0].isBlank() || !p[0].matches("(\\d{1,10})+([.|,]\\d)*")) ? null : BigDecimal.valueOf(Float.valueOf(p[0])).setScale(l, RoundingMode.UP).floatValue();
	
	/**
	 * Get ArrayList Value of Query parameters.
	 *
	 * @param p		The values array.
	 * @param l		The maximum length allowed for the value.
	 * @param c		The value's class.
	 * @return		The validated value.
	 */
	static TriFunction<String[], Integer, Class<?>, List<?>> getArrayListValue = (a, l, c) -> {
		
		return a != null && a.length > 0
				? Arrays.asList(a)
					.stream()
					.map(e -> {
						return c == String.class 
								? getStringValue.apply(new String[] {e}, l) 
								: c == Integer.class 
									? getIntegerValue.apply(new String[] {e}, l)
									: null;
					})
					.collect(Collectors.toList())
				: new ArrayList<>();
	};
	
	/**
	 * Get Boolean Value of Query parameters.
	 *
	 * @param p		The values array.
	 * @return		The validated value.
	 */
	static Function<String[], Boolean> getBooleanValue = p -> p == null || p[0] == null || p[0] != null && (p[0].isBlank() || !p[0].matches("(?i)^(true|false)$")) ? false : p[0].equalsIgnoreCase("true");
	
	/**
	 * Get Date Value of Query parameters.
	 *
	 * @param p		The values array.
	 * @return		The validated value.
	 */
	static Function<String[], LocalDate> getDateValue = p -> {
		return p != null && p[0] == null && p[0].matches("(\\d{2}-\\d{2}-\\d{4})")
				? LocalDate.of(Integer.valueOf(p[0].split("-")[2]), Integer.valueOf(p[0].split("-")[1]), Integer.valueOf(p[0].split("-")[0]))
				: null;
	};
	
	/**
	 * Get Date pattern Value of Query parameters.
	 *
	 * @param d		The date pattern value.
	 * @return		The validated value.
	 */
	static Function<String[], String> getDatePatternValue = (d -> {
		String datePattern = "%-%-%";
		
		if (d != null && d[0] != null && !d[0].isBlank() && d[0].matches("(\\d{2}-\\d{2}-\\d{4})")) {
			String[] dateParts = d[0].split("-");
			
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