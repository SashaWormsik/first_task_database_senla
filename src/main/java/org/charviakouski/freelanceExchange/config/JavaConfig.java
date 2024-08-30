package org.charviakouski.freelanceExchange.config;

import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    public EntityMapper entityMapper() {
        return new EntityMapper();
    }
}
