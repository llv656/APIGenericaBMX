package com.sssolutions.bmx.APIGenericaBMX.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy 
@ComponentScan(basePackages = "com.sssolutions.bmx.APIGenericaBMX.aspects")
public class AspectConfig {}