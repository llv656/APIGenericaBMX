package com.sssolutions.bmx.APIGenericaBMX.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/*{CHANGE_ARTEFACT}
 * 
 * */
@Configuration
@PropertySource(value = "file:${BMX_ENVIRONMENT}/APIGenericaBMX.properties", ignoreResourceNotFound = false)
public class PropertyConfig {
	
	private static final Logger logger = LogManager.getLogger(PropertyConfig.class);
	
	@Autowired
    private Environment env;
	
	public String getPropertyString(String property) {      
        
		String propertyValue = null;
        
		try {
			
			propertyValue = env.getProperty(property);
			
		} catch(Exception e) {
            
            	logger.error("Error reading property: ".concat(property));
            	logger.error("*property String: ".concat(e.getMessage()));
            	
        }
		
	    return propertyValue;
	    
	}

	public Integer getPropertyInteger(String property) {
		
		Integer propertyValue = null;
        
		try {
			
			propertyValue = env.getProperty(property, Integer.class);
			
        } catch(Exception e) {
        	
        	logger.error("Error reading property: ".concat(property));
        	logger.error("*property Integer:".concat(e.getMessage()));
        	
        }
		
        return propertyValue;
        
	}
	
}