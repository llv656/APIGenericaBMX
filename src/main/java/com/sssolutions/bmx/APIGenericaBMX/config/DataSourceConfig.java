package com.sssolutions.bmx.APIGenericaBMX.config;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.sssolutions.bmx.APIGenericaBMX.values.Properties;
import com.sssolutions.bmx.RepositoryBMX.DaoBMX;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableAsync
public class DataSourceConfig {
	
	private static final Logger LOGGER = LogManager.getLogger(DataSourceConfig.class);

	@Autowired
	private PropertyConfig property;

	@Bean(name = "dsSSS")
    DataSource getDataSource() {
        LOGGER.info("Inicia DataSource");
        HikariDataSource dataSource= new HikariDataSource();
        dataSource.setDriverClassName(property.getPropertyString(Properties.JDBC_DRIVER));
        dataSource.setJdbcUrl(property.getPropertyString(Properties.JDBC_URL).concat(property.getPropertyString(Properties.JDBC_DATABASE)));
        dataSource.setUsername(property.getPropertyString(Properties.JDBC_USERNAME));
        dataSource.setPassword(property.getPropertyString(Properties.JDBC_PASSWORD));
        dataSource.setMaximumPoolSize(15);
        dataSource.setMinimumIdle(5);
        return dataSource;
    }
	
	@Bean
	DaoBMX getDaoBMX() {
		return new DaoBMX();
	}
	
}
