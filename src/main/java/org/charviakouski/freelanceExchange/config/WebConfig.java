package org.charviakouski.freelanceExchange.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("org.charviakouski.freelanceExchange")
public class WebConfig {
}
