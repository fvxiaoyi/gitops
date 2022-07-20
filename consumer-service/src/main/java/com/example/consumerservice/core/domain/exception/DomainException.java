package com.example.consumerservice.core.domain.exception;

import com.example.consumerservice.core.base.BaseRuntimeException;

/**
 * @author ebin
 */
public abstract class DomainException extends BaseRuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, String errorCode) {
        super(message, errorCode);
    }

    public DomainException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }
}
