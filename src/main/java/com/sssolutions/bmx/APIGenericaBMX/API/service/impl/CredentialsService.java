package com.sssolutions.bmx.APIGenericaBMX.API.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sssolutions.bmx.APIGenericaBMX.API.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.API.model.WebAppCredentialsModel;
import com.sssolutions.bmx.APIGenericaBMX.API.service.ICredentialsService;
import com.sssolutions.bmx.APIGenericaBMX.BD.dao.ICredencialesApiBdRepository;
import com.sssolutions.bmx.APIGenericaBMX.BD.entity.CredencialesApiBdEntity;
import com.sssolutions.bmx.APIGenericaBMX.config.PropertyConfig;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseServiceDTO;
import com.sssolutions.bmx.APIGenericaBMX.values.Messages;
import com.sssolutions.bmx.APIGenericaBMX.values.Properties;
import com.sssolutions.bmx.CryptoBMX.CryptoBMX;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CredentialsService implements ICredentialsService {
	
	private static final Logger LOGGER = LogManager.getLogger(CredentialsService.class);
	private ICredencialesApiBdRepository credencialesAPIBdRepository;
	private PropertyConfig property;
	private CryptoBMX cryptoSecurity;

	@Override
	@Async
	public CompletableFuture<ResponseServiceDTO> executeGetDataSourceWebApp(Map<String, String> headers, APIModel propertiesRequest) {
		LOGGER.info("\t\tMethod:".concat(new Object(){}.getClass().getEnclosingMethod().getName()));

		ResponseServiceDTO responseDTO = new ResponseServiceDTO();
		
		LOGGER.info("\t\tDTO credenciales app web ");
		WebAppCredentialsModel webAppCredentials = new WebAppCredentialsModel();
		webAppCredentials.setWebAppID(UUID.fromString(StringEscapeUtils.escapeSql(headers.get("x_web_app_id"))));
		webAppCredentials.setWebAppKey(StringEscapeUtils.escapeSql(headers.get("x_web_app_key")));
		
		LOGGER.info("\t\tRecuperación de usuario API BD");
		Map<String, String> response = new HashMap<>();
		ArrayList<String> errMsgDetails = new ArrayList<>();
		try {
			CredencialesApiBdEntity credentialsEntity = credencialesAPIBdRepository.getBDAPICredentials(
					webAppCredentials.getWebAppID(), 
					(short) propertiesRequest.getIdApi(), 
					(short) propertiesRequest.getIdEndpoint());
			
			response.put("api.db.driver", property.getPropertyString(Properties.JDBC_DRIVER));
			response.put("api.db.serverAddress", property.getPropertyString(Properties.JDBC_URL));
			response.put("api.db.database", credentialsEntity.getBdName());
			response.put("api.db.schema", credentialsEntity.getSchemaBd());
			
			Map<String, String> credentials = cryptoSecurity.getCredentialsBD_BMX(
					credentialsEntity.getUsrBd(), credentialsEntity.getPassBd(), 
					webAppCredentials.getWebAppKey(), 
					property.getPropertyString(Properties.CRYPTO_KEY_SEPARATOR)
					);
			response.put("api.db.usr", credentials.get("usr"));
			response.put("api.db.passwd", credentials.get("pass"));
			
			responseDTO.setValid(true);
			responseDTO.setResult(response);
		} catch (Exception e) {
			LOGGER.error("\t\t".concat(e.toString()));
			LOGGER.error("\t\t".concat(e.getMessage()));
			
			errMsgDetails.add(e.getClass().getName());
			responseDTO.setValid(false);
			responseDTO.setMessage(Messages.ERROR_001);
			responseDTO.setDetails(errMsgDetails.toArray(new String[errMsgDetails.size()]));
		}
		
		LOGGER.info("\t\tValidación de respuesta del servicio");
		if (!responseDTO.isValid()) {
			responseDTO.setMessage(Messages.ERROR_001);
			responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST);
		}
		
		return CompletableFuture.completedFuture(responseDTO);
	}

}
