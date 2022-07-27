package com.example.consumerservice.core.web.exception;

import java.util.List;

/**
 * @author ebin
 */
@FunctionalInterface
public interface ExceptionHandlerCustomizer {
    List<ExceptionHandler> exceptionHandlers();
}