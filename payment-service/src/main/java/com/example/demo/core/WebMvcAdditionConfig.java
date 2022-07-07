package com.example.demo.core;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Conventions;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

@Configuration
public class WebMvcAdditionConfig {
    @Bean
    public WebMvcRegistrations webMvcRegistrations() {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
                return new MyRequestMappingHandlerAdapter();
            }
        };
    }

    static class MyRequestMappingHandlerAdapter extends RequestMappingHandlerAdapter {
        @Override
        public void afterPropertiesSet() {
            addCustomArgumentResolvers(new MyHandlerMethodArgumentResolver(getMessageConverters()));
            super.afterPropertiesSet();
        }

        public void addCustomArgumentResolvers(HandlerMethodArgumentResolver resolver) {
            List<HandlerMethodArgumentResolver> customArgumentResolvers = getCustomArgumentResolvers();
            if (Objects.isNull(customArgumentResolvers)) {
                customArgumentResolvers = List.of(resolver);
                setCustomArgumentResolvers(customArgumentResolvers);
            } else {
                customArgumentResolvers.add(resolver);
                setCustomArgumentResolvers(customArgumentResolvers);
            }
        }
    }

    static class MyHandlerMethodArgumentResolver extends RequestResponseBodyMethodProcessor {
        public MyHandlerMethodArgumentResolver(List<HttpMessageConverter<?>> converters) {
            super(converters);
        }

        @Override
        public boolean supportsParameter(MethodParameter parameter) {
            if (!parameter.hasParameterAnnotations() &&
                    AnnotatedElementUtils.hasAnnotation(parameter.getContainingClass(), ResponseBody.class)) {
                Method method = parameter.getMethod();
                if (Objects.nonNull(method)) {
                    return AnnotatedElementUtils.hasAnnotation(parameter.getMethod(), PostMapping.class)
                            || AnnotatedElementUtils.hasAnnotation(parameter.getMethod(), PutMapping.class)
                            || AnnotatedElementUtils.hasAnnotation(parameter.getMethod(), PostMapping.class)
                            || AnnotatedElementUtils.hasAnnotation(parameter.getMethod(), DeleteMapping.class);
                }
            }
            return false;
        }

        @Override
        public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
            parameter = parameter.nestedIfOptional();
            Object arg = readWithMessageConverters(webRequest, parameter, parameter.getNestedGenericParameterType());
            String name = Conventions.getVariableNameForParameter(parameter);

            if (binderFactory != null) {
                WebDataBinder binder = binderFactory.createBinder(webRequest, arg, name);
                if (arg != null) {
                    validateIfApplicable(binder, parameter);
                    if (binder.getBindingResult().hasErrors() && isBindExceptionRequired(binder, parameter)) {
                        throw new MethodArgumentNotValidException(parameter, binder.getBindingResult());
                    }
                }
                if (mavContainer != null) {
                    mavContainer.addAttribute(BindingResult.MODEL_KEY_PREFIX + name, binder.getBindingResult());
                }
            }

            return adaptArgumentIfNecessary(arg, parameter);
        }

        @Override
        protected void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
            binder.validate();
        }

        @Override
        public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
            super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        }
    }
}
