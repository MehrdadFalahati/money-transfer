package com.github.mehrdad.falahati.money.transfer.domain.exception;

import com.github.mehrdad.falahati.common.domain.exception.DomainException;

public class NotFoundException extends DomainException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
