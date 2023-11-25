package com.sssolutions.bmx.APIGenericaBMX.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@SuppressWarnings({ "removal", "deprecation" })
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                    .permitAll()
                    	.and()
                    		.csrf().disable();
        return http.build();
    }
	
}