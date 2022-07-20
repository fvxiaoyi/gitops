package com.example.consumerservice.core.mvc;

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;

/**
 * @author ebin
 */
public class AnnotationLessRequestMappingRegistrationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        AnnotationLessRequestMappingHandlerAdapter annotationLessRequestMappingHandlerAdapter = applicationContext.getBean(AnnotationLessRequestMappingHandlerAdapter.class);
        AnnotationLessHandlerMethodArgumentResolver resolver = annotationLessRequestMappingHandlerAdapter.getAnnotationLessHandlerMethodArgumentResolver();
        if (Objects.isNull(resolver)) {
            return;
        }
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Collection<HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods().values();
        for (HandlerMethod handlerMethod : handlerMethods) {
            boolean applicable = false;
            if (handlerMethod.getBeanType().equals(BasicErrorController.class)) {
                continue;
            }
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
            for (MethodParameter methodParameter : methodParameters) {
                Field[] declaredFields = methodParameter.getParameterType().getDeclaredFields();
                for (Field field : declaredFields) {
                    Annotation[] annotations = field.getAnnotations();
                    for (Annotation ann : annotations) {
                        if (ann.annotationType().getName().startsWith("javax.validation")) {
                            resolver.addValidateRequestParamNames(methodParameter.getParameterType().getName());
                            applicable = true;
                            break;
                        }
                    }
                    if (applicable) {
                        break;
                    }
                }
                if (applicable) {
                    break;
                }
            }
        }
    }
}
