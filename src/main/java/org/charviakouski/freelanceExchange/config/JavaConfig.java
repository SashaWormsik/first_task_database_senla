package org.charviakouski.freelanceExchange.config;

import org.charviakouski.freelanceExchange.MyApplication;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = MyApplication.class)
public class JavaConfig {

    @Bean
    public EntityMapper entityMapper() {
        return new EntityMapper();
    }
}
