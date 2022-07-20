package com.example.consumerservice.core.mvc;

import java.util.List;

/**
 * @author ebin
 */
@FunctionalInterface
public interface ExceptionHandlerCustomizer {
    List<ExceptionHandler> exceptionHandlers();
}
