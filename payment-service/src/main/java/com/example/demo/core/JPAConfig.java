package com.example.demo.core;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.ValidationMode;
import java.sql.Connection;

@Configuration
public class JPAConfig {
    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer() {
        return prop -> {
            prop.putIfAbsent(AvailableSettings.JPA_VALIDATION_MODE, ValidationMode.AUTO);
            prop.putIfAbsent(AvailableSettings.ISOLATION, Connection.TRANSACTION_READ_COMMITTED);
            prop.putIfAbsent(AvailableSettings.STATEMENT_FETCH_SIZE, 64);
        };
    }
}
