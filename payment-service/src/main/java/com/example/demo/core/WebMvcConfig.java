package com.example.demo.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new MyHandlerExceptionResolver());
    }

    static class MyHandlerExceptionResolver extends AbstractHandlerExceptionResolver {
        private final Logger logger = LoggerFactory.getLogger(MyHandlerExceptionResolver.class);
        private final MappingJackson2JsonView jsonView;

        public MyHandlerExceptionResolver() {
            jsonView = new MappingJackson2JsonView();
            jsonView.setExtractValueFromSingleKeyModel(true);
        }

        @Override
        protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
            ModelAndView mv = new ModelAndView();

            ResponseStatus responseStatus = ex.getClass().getDeclaredAnnotation(ResponseStatus.class);
            if (responseStatus == null) {
                mv.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                mv.setStatus(responseStatus.value());
            }

            Map<String, Object> cmdMsg = new HashMap<>();
            if (ex instanceof MethodArgumentNotValidException) {
                MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
                cmdMsg.put("msg", e.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(",")));
            } else if (ex instanceof ConstraintViolationException) {
                cmdMsg.put("msg", ((ConstraintViolationException) ex).getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(",")));
            } else {
                cmdMsg.put("msg", ex.getMessage());
            }

            if (ex instanceof BusinessException) {
                cmdMsg.put("error_code", ((BusinessException) ex).errorCode());
                response.setHeader("error_code", ((BusinessException) ex).errorCode());
            } else {
                cmdMsg.put("error_code", "INTERNAL_ERROR");
                response.setHeader("error_code", "INTERNAL_SERVER_ERROR");
            }

            mv.addObject("exception", cmdMsg);
            mv.setView(jsonView);
            return mv;
        }

        @Override
        protected void logException(Exception ex, HttpServletRequest request) {
            logger.error(ex.getMessage(), ex);
        }

    }
}
