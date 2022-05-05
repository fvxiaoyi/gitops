package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            cmdMsg.put("msg", ex.getMessage());

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
