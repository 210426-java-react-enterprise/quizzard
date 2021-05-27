package com.revature.quizzard.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.revature")
@PropertySource("classpath:app.properties")
@Import({AopConfig.class, OrmConfig.class, MvcConfig.class})
public class AppConfig {
}
