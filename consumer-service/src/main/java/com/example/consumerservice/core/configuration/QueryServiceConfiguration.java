package com.example.consumerservice.core.configuration;

import com.example.consumerservice.core.db.query.JPAQueryService;
import com.example.consumerservice.core.db.query.MyBatisQueryParser;
import com.example.consumerservice.core.db.query.QueryParser;
import com.example.consumerservice.core.db.query.QueryService;
import com.example.consumerservice.core.db.query.SqlQueryServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Validator;

/**
 * @author ebin
 */
@Configuration
@EnableConfigurationProperties({SqlQueryServiceProperties.class})
public class QueryServiceConfiguration {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public QueryService jpaSqlQueryService(@Autowired QueryParser queryParser, @Autowired Validator validator) {
        return new JPAQueryService(entityManager, queryParser, validator);
    }

    @Bean
    public QueryParser queryParser(@Autowired SqlQueryServiceProperties sqlQueryServiceProperties) {
        return new MyBatisQueryParser(sqlQueryServiceProperties);
    }
}
