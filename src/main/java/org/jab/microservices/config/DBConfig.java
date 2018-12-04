package org.jab.microservices.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBConfig.class);

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.postgres")
    public DataSource postgresDataSource() {

        LOGGER.info("Loading database configuration bean");

        return DataSourceBuilder.create().build();
    }


}
