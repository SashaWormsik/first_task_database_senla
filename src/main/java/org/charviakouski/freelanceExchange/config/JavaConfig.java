package org.charviakouski.freelanceExchange.config;

import liquibase.integration.spring.SpringLiquibase;
import org.charviakouski.freelanceExchange.MyApplication;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = MyApplication.class)
@PropertySource("classpath:application.properties")
public class JavaConfig {
    @Value("${spring.datasource.driver}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${liquibase.changeLog.file}")
    private String changeLogFile;
    @Value("${liquibase.schema.database}")
    private String liquibaseSchema;

    @Bean
    public EntityMapper entityMapper() {
        return new EntityMapper();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setChangeLog(changeLogFile);
        springLiquibase.setDefaultSchema(liquibaseSchema);
        springLiquibase.setDataSource(dataSource);
        return springLiquibase;
    }
}
