package com.example.consumerservice;

import core.framework.configuration.DataSourceConfiguration;
import core.framework.configuration.DefaultWebMvcConfigurer;
import core.framework.configuration.HibernateJPAConfiguration;
import core.framework.configuration.QueryServiceConfiguration;
import core.framework.configuration.WebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({DataSourceConfiguration.class,
        DefaultWebMvcConfigurer.class,
        HibernateJPAConfiguration.class,
        QueryServiceConfiguration.class,
        WebMvcConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
