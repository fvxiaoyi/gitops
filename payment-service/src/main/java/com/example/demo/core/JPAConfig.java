package com.example.demo.core;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.event.spi.EventType;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.ValidationMode;
import java.sql.Connection;

@Configuration
public class JPAConfig {
    private static final String EVENT_LISTENER_GROUP_NAME_TPL = "%s.%s";

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer() {
        return prop -> {
            prop.putIfAbsent(AvailableSettings.JPA_VALIDATION_MODE, ValidationMode.AUTO);
            prop.put(hibernateEventListenerGroupName(EventType.PRE_INSERT), hibernatePreInsertEventListenerNames());
            prop.put(hibernateEventListenerGroupName(EventType.PRE_UPDATE), hibernatePreUpdateEventListenerNames());
            prop.put(hibernateEventListenerGroupName(EventType.PRE_DELETE), hibernatePreDeleteEventListenerNames());
            prop.putIfAbsent(AvailableSettings.ISOLATION, Connection.TRANSACTION_READ_COMMITTED);
            prop.putIfAbsent(AvailableSettings.STATEMENT_FETCH_SIZE, 64);
        };
    }

    protected String hibernateEventListenerGroupName(EventType<?> eventType) {
        return String.format(EVENT_LISTENER_GROUP_NAME_TPL, AvailableSettings.EVENT_LISTENER_PREFIX, eventType);
    }

    protected String hibernatePreInsertEventListenerNames() {
        return HibernateListener.class.getTypeName();
    }

    protected String hibernatePreUpdateEventListenerNames() {
        return HibernateListener.class.getTypeName();
    }

    protected String hibernatePreDeleteEventListenerNames() {
        return HibernateListener.class.getTypeName();
    }
}
