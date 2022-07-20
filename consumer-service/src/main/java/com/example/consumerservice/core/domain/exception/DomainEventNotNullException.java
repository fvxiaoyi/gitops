package com.example.consumerservice.core.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ebin
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DomainEventNotNullException extends DomainException {
    public DomainEventNotNullException() {
        super("Domain event must not be null!", "CONFLICT");
    }
}
