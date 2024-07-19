package com.sssolutions.bmx.APIGenericaBMX.API.aspects;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.sssolutions.bmx.APIGenericaBMX.API.controller.utils.APIUtils;
import com.sssolutions.bmx.APIGenericaBMX.API.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.API.service.ICredentialsService;
import com.sssolutions.bmx.RepositoryBMX.DaoBMX;
import com.sssolutions.bmx.dto.ResponseServiceDTO;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class PreProcessingSetupAspect {
	private static final Logger LOGGER = LogManager.getLogger(PreProcessingSetupAspect.class);
	
	private HttpServletRequest request;
	private APIUtils apiService;
	private ICredentialsService credentialsService;
	private DaoBMX bdService;
	
	private static final ThreadLocal<NamedParameterJdbcTemplate> jdbcTemplate_PLSSS = new ThreadLocal<>();
	private static final ThreadLocal<CompletableFuture<APIModel>> properties = new ThreadLocal<>();
	public static final Supplier<NamedParameterJdbcTemplate> getJdbcTemplate_PLSSS = () -> jdbcTemplate_PLSSS.get();
    public static final Supplier<CompletableFuture<APIModel>> asyncRequestProperties = () -> properties.get();
    
    private ThreadLocal<Boolean> wasExecute = ThreadLocal.withInitial(() -> false);
    
    public PreProcessingSetupAspect(
    		HttpServletRequest request,
    		APIUtils apiService,
    		ICredentialsService credentialsService,
    		DaoBMX bdService) {
    	this.request = request;
    	this.apiService = apiService;
    	this.credentialsService = credentialsService;
    	this.bdService = bdService;
    }
    
    @Before("execution(* com.sssolutions.bmx.APIGenericaBMX.API.model.validator.*.*(..))")
    public void beforeValidator(JoinPoint joinPoint) {
    	beforeControllerExecute(joinPoint);
    	wasExecute.set(true);
    }
    
    @Before("execution(* com.sssolutions.bmx.APIGenericaBMX.API.controller.*.*(..))")
    public void beforeControllerExecute(JoinPoint joinPoint) {
    	if (!wasExecute.get()) {
        	String webAppId = request.getHeader("X_WEB_APP_ID");
        	UUID webAppUUID = UUID.fromString(webAppId);
            String webAppKey = request.getHeader("X_WEB_APP_KEY");
            String addr = request.getRemoteAddr();
            String uri = request.getRequestURI();
            String httpMethod = request.getMethod();
            String method = httpMethod.concat("-").concat(uri);
            
            CompletableFuture<APIModel> propertiesRequest;
            CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponse;
            NamedParameterJdbcTemplate jdbcTemplate;
            
        	LOGGER.info("**Ajustes previos de solicitud - ".concat(httpMethod).concat(" - ").concat(uri));
            
            LOGGER.info("\tConfiguración de propiedades de solicitud");
    		propertiesRequest = apiService.getpropertiesRequest(addr, method);
    		properties.set(propertiesRequest);
    		
    		if (bdService.existJdbcTemplate.test(webAppUUID)) {
    			LOGGER.info("\tJDBC Template existente");
    			jdbcTemplate = bdService.getDataSource.apply(webAppUUID);
    		} else {
    			LOGGER.info("\tEmpieza servicio de recuperar credenciales BD");
        		credentiaslAsyncResponse = credentialsService.executeGetDataSourceWebApp(webAppId, webAppKey, propertiesRequest);
        		jdbcTemplate = bdService.jdbcTemplatePLPSSS.apply(UUID.fromString(webAppId), credentiaslAsyncResponse);
    		}
    		
    		LOGGER.info("\tJDBC Template PL-PSSS Listo");
    		jdbcTemplate_PLSSS.set(jdbcTemplate);
    	}
    }
    
    @After("execution(* com.sssolutions.bmx.APIGenericaBMX.API.controller.*.*(..))")
    public void afterControllerExecute() {
    	jdbcTemplate_PLSSS.remove();
    	properties.remove();
    	wasExecute.remove();
    }
}