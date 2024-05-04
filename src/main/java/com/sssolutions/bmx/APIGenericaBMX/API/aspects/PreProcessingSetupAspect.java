package com.sssolutions.bmx.APIGenericaBMX.API.aspects;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.sssolutions.bmx.APIGenericaBMX.API.controller.utils.APIService;
import com.sssolutions.bmx.APIGenericaBMX.API.model.APIModel;
import com.sssolutions.bmx.APIGenericaBMX.API.service.ICredentialsService;
import com.sssolutions.bmx.dto.ResponseServiceDTO;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class PreProcessingSetupAspect {
	private static final Logger LOGGER = LogManager.getLogger(PreProcessingSetupAspect.class);
	
	private HttpServletRequest request;
	private APIService apiService;
	private ICredentialsService credentialsService;
	
	private static final ThreadLocal<CompletableFuture<ResponseServiceDTO>> credentials = new ThreadLocal<>();
	private static final ThreadLocal<CompletableFuture<APIModel>> properties = new ThreadLocal<>();
	public static final Supplier<CompletableFuture<ResponseServiceDTO>> asyncCredentials = () -> credentials.get();
    public static final Supplier<CompletableFuture<APIModel>> asyncRequestProperties = () -> properties.get();
    
    private ThreadLocal<Boolean> wasExecute = ThreadLocal.withInitial(() -> false);
    
    public PreProcessingSetupAspect(
    		HttpServletRequest request,
    		APIService apiService,
    		ICredentialsService credentialsService
    		) {
    	this.request = request;
    	this.apiService = apiService;
    	this.credentialsService = credentialsService; 
    }
    
    @Before("execution(* com.sssolutions.bmx.APIGenericaBMX.API.controller.*.*(..))")
    public void beforeControllerExecute(JoinPoint joinPoint) {
    	if (!wasExecute.get()) {
    		String uri = request.getRequestURI();
        	String webAppId = request.getHeader("X_WEB_APP_ID");
            String webAppKey = request.getHeader("X_WEB_APP_KEY");
            String addr = request.getRemoteAddr();
            String httpMethod = request.getMethod();
            String method = httpMethod.concat("-").concat(uri);//joinPoint.getSignature().getName();
            
        	LOGGER.info("**Ajustes previos de solicitud - ".concat(httpMethod).concat(" - ").concat(uri));
            
            LOGGER.info("\tConfiguración de propiedades de solicitud");
    		CompletableFuture<APIModel> propertiesRequest = apiService.getpropertiesRequest(addr, method);
    		
    		LOGGER.info("\tEmpieza servicio de recuperar credenciales BD");
    		CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponse = credentialsService.executeGetDataSourceWebApp(webAppId, webAppKey, propertiesRequest).toCompletableFuture();
            credentials.set(credentiaslAsyncResponse);
            properties.set(propertiesRequest);
    	}
    }
    
    @Before("execution(* com.sssolutions.bmx.APIGenericaBMX.API.validation.*.*(..))")
    public void beforeValidator(JoinPoint joinPoint) {
    	this.beforeControllerExecute(joinPoint);
    	this.wasExecute.set(true);
    }
}