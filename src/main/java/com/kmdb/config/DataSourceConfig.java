/*package com.kmdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driverClassName")); // Matches "driverClassName" in application.properties
        dataSource.setUrl(env.getProperty("spring.datasource.url")); // Matches "url" in application.properties
        dataSource.setUsername(env.getProperty("username")); // Matches "username" in application.properties
        dataSource.setPassword(env.getProperty("password")); // Matches "password" in application.properties
        return dataSource;
    }
}*/
