package com.sssolutions.bmx.APIGenericaBMX.dao.utils;

import java.time.LocalDate;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class SQLUtilsService {
	
	@Async
	public String setArraySmallInt(int[] intArray) {
		String arraySmallInt = "";
		
		for (int i: intArray) {
			arraySmallInt = arraySmallInt.concat(String.valueOf(i)).concat("::SMALLINT,");
		}
		
		return "ARRAY[".concat(arraySmallInt.substring(0, arraySmallInt.length()-1))
						.concat("]");
	}
	
	@Async
	public String setCastSmallInt(int integer) {
		return "CAST(".concat(integer < 0 
								? "NULL"
								: String.valueOf(integer))
						.concat(" AS SMALLINT)");
	}
	
	@Async
	public String setCastSmallIntWhitValueDefault(int integer, int valueDefault) {
		return "CAST(".concat(integer < 0 
								? String.valueOf(valueDefault)
								: String.valueOf(integer))
						.concat(" AS SMALLINT)");
	}
	
	@Async
	public String setCastUUID(String uuid) {
		return "CAST('".concat(uuid).concat("' AS UUID)");
	}
	
	public String getStringOrNullValue (String string) {
		
		if (string != null
				&& !string.isEmpty()
				&& !string.isBlank()) {
		
			string = "'".concat(string.trim()).concat("'");
		} else {
			string =  "null";
		}
		
		return string;
	}
	
	public String getLocalDateOrNullValue (LocalDate localDate) {
		String string;
		if (localDate != null) {
		
			string = "'".concat(localDate.toString()).concat("'");
		} else {
			string =  "null";
		}
		
		return string;
	}
	
}
