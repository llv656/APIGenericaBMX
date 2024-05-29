package com.sssolutions.bmx.APIGenericaBMX.BD.entity.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;

import org.springframework.stereotype.Component;

import com.sssolutions.bmx.APIGenericaBMX.API.model.PagingModel;
import com.sssolutions.bmx.APIGenericaBMX.API.model.PagingSettingsModel;
import com.sssolutions.bmx.APIGenericaBMX.config.PropertyConfig;
import com.sssolutions.bmx.APIGenericaBMX.values.Properties;

import lombok.AllArgsConstructor;

/**
 * This class provides utility methods for entities paging.
 * 
 * @author Lenin Leines Vite
 * @version 1.0.0
 */
@Component
@AllArgsConstructor
public class PagingUtils {
	
	private PropertyConfig property;
	
	/**
	 * configure data pagination.
	 *
	 * @param tR	The total record of the dao response.
	 * @param pS	The PagingSettingsModel to build the paging settings.
	 * @return		The PagingModel containing the response.
	 */
	public BiFunction<Integer, PagingSettingsModel, PagingModel> config = ((tR, pS) -> {
		PagingModel paging = new PagingModel();
		
		int pageSize = pS.getPageSize() == 0 
				? property.getPropertyInteger(Properties.DEFAULT_PAGE_SIZE) : pS.getPageSize();
		
		int pageNumber = pS.getPageNumber() == 0
				? property.getPropertyInteger(Properties.DEFAULT_PAGE_NUMBER) : pS.getPageNumber();
		
		int totalPages = BigDecimal.valueOf(tR / pageSize).setScale(0, RoundingMode.CEILING).intValue();
		
		paging.setItemsPage(pageSize);
		paging.setActualPage(pageNumber);
		paging.setTotalPages(totalPages == 0 && tR > 0 
				? 1 : totalPages);
		paging.setTotalItems(tR);
		return paging;
	});

}
