package com.example.consumerservice.core.configuration;

import com.example.consumerservice.core.web.expand.AnnotationLessRequestMappingHandlerAdapter;
import com.example.consumerservice.core.web.expand.AnnotationLessRequestMappingRegistrationListener;
import com.example.consumerservice.core.utils.json.JSONMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.validation.Validator;

@Configuration
public class WebMvcConfiguration {
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return JSONMapper.OBJECT_MAPPER;
    }

    @Bean
    public AnnotationLessRequestMappingRegistrationListener annotationLessRequestMappingRegistrationListener() {
        return new AnnotationLessRequestMappingRegistrationListener();
    }

    @Bean
    public WebMvcRegistrations webMvcRegistrations(@Autowired Validator validator) {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
                return new AnnotationLessRequestMappingHandlerAdapter(validator);
            }
        };
    }
}
