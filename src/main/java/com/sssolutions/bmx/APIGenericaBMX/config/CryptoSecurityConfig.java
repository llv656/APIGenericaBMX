package com.sssolutions.bmx.APIGenericaBMX.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sssolutions.bmx.CryptoBMX.CryptoBMX;

@Configuration
public class CryptoSecurityConfig {

	@Bean
	CryptoBMX getCryptoBMX() {
		return new CryptoBMX(); 
	}
	
}
