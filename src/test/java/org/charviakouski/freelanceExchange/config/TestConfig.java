package org.charviakouski.freelanceExchange.config;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("org.charviakouski.freelanceExchange.repository")
@PropertySource("classpath:applicationTest.properties")
public class TestConfig {

    @Value("${test.spring.datasource.driver}")
    private String driver;
    @Value("${test.spring.datasource.url}")
    private String url;
    @Value("${test.spring.datasource.username}")
    private String userName;
    @Value("${test.spring.datasource.password}")
    private String password;
    @Value("${test.hibernate.dialect}")
    private String hibernateDialect;
    @Value("${test.hibernate.format_sql}")
    private String hibernateFormatSql;
    @Value("${test.hibernate.show_sql}")
    private String hibernateShowSql;
    @Value("${test.hibernate.hbm2ddl.auto}")
    private String autoDdlCreation;

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
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan("org.charviakouski.freelanceExchange.model.entity");
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setPersistenceProvider(new HibernatePersistenceProvider());
        entityManager.setJpaProperties(getHibernateProperties());
        return entityManager;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }


    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.format_sql", hibernateFormatSql);
        properties.setProperty("hibernate.hbm2ddl.auto", autoDdlCreation);
        return properties;
    }
}