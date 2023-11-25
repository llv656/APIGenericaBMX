package com.sssolutions.bmx.APIGenericaBMX.config;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.sssolutions.bmx.APIGenericaBMX.values.Properties;

@Configuration
public class DataSourceConfig {
	
	private static final Logger LOGGER = LogManager.getLogger(DataSourceConfig.class);

	@Autowired
	private PropertyConfig property;

    @Bean(name = "dsSSS")
    @Primary
    DataSource getDataSource() {
        LOGGER.info("Inicia DataSource");
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(property.getPropertyString(Properties.JDBC_DRIVER));
        dataSourceBuilder.url(property.getPropertyString(Properties.JDBC_URL).concat(property.getPropertyString(Properties.JDBC_DATABASE)));
        dataSourceBuilder.username(property.getPropertyString(Properties.JDBC_USERNAME));
        dataSourceBuilder.password(property.getPropertyString(Properties.JDBC_PASSWORD));
        return dataSourceBuilder.build();
    }
	
}
