package com.example.consumerservice.core.configuration;

import com.example.consumerservice.core.event.DomainEventRegistrationListener;
import com.example.consumerservice.core.event.HibernatePostCommitEventListener;
import com.example.consumerservice.core.event.HibernatePreCommitEventListener;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.event.spi.EventType;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.persistence.ValidationMode;
import java.sql.Connection;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class HibernateJPAConfiguration {
    public static final String DOMAIN_EVENT_TASK_EXECUTOR_NAME = "domainEventTaskExecutor";
    private static final String EVENT_LISTENER_GROUP_NAME_TPL = "%s.%s";

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer() {
        return prop -> {
            prop.put(hibernateEventListenerGroupName(EventType.POST_INSERT), HibernatePreCommitEventListener.class.getTypeName());
            prop.put(hibernateEventListenerGroupName(EventType.POST_UPDATE), HibernatePreCommitEventListener.class.getTypeName());
            prop.put(hibernateEventListenerGroupName(EventType.POST_DELETE), HibernatePreCommitEventListener.class.getTypeName());

            prop.put(hibernateEventListenerGroupName(EventType.POST_COMMIT_INSERT), HibernatePostCommitEventListener.class.getTypeName());
            prop.put(hibernateEventListenerGroupName(EventType.POST_COMMIT_UPDATE), HibernatePostCommitEventListener.class.getTypeName());
            prop.put(hibernateEventListenerGroupName(EventType.POST_COMMIT_DELETE), HibernatePostCommitEventListener.class.getTypeName());

            prop.putIfAbsent(AvailableSettings.JPA_VALIDATION_MODE, ValidationMode.NONE);
            prop.putIfAbsent(AvailableSettings.ISOLATION, Connection.TRANSACTION_READ_COMMITTED);
            prop.putIfAbsent(AvailableSettings.STATEMENT_FETCH_SIZE, 64);
        };
    }

    @Bean
    public DomainEventRegistrationListener domainEventRegistrationListener() {
        return new DomainEventRegistrationListener();
    }

    @Bean(name = DOMAIN_EVENT_TASK_EXECUTOR_NAME)
    public ThreadPoolTaskExecutor domainEventTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadPriority(Thread.MAX_PRIORITY);
        taskExecutor.setThreadGroupName("DomainEventThreadGroup");
        taskExecutor.setThreadNamePrefix("DomainEventThread");
        taskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors() + 1);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60 * 2);
        taskExecutor.initialize();
        return taskExecutor;
    }

    protected String hibernateEventListenerGroupName(EventType<?> eventType) {
        return String.format(EVENT_LISTENER_GROUP_NAME_TPL, AvailableSettings.EVENT_LISTENER_PREFIX, eventType);
    }
}
