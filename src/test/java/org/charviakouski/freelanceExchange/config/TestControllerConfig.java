package org.charviakouski.freelanceExchange.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(basePackages = "org.charviakouski.freelanceExchange", excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Configuration.class}) )
@Configuration
public class TestControllerConfig {
}
