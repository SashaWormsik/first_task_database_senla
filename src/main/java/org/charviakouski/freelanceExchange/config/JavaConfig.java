package org.charviakouski.freelanceExchange.config;

import liquibase.Liquibase;
import liquibase.integration.spring.SpringLiquibase;
import org.charviakouski.freelanceExchange.MyApplication;
import org.charviakouski.freelanceExchange.connection.ConnectionFactory;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.util.PropertyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = MyApplication.class)
public class JavaConfig {
    @Autowired
    private PropertyUtil propertyUtil;

    @Bean
    public EntityMapper entityMapper() {
        return new EntityMapper();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(propertyUtil.getProperty("driver"));
        dataSource.setUrl(propertyUtil.getProperty("url"));
        dataSource.setUsername(propertyUtil.getProperty("username"));
        dataSource.setPassword(propertyUtil.getProperty("password"));
        return dataSource;
    }

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setChangeLog(propertyUtil.getProperty("changeLogFile"));
        springLiquibase.setDefaultSchema(propertyUtil.getProperty("liquibaseSchema"));
        springLiquibase.setDataSource(dataSource);
        return springLiquibase;
    }
}
