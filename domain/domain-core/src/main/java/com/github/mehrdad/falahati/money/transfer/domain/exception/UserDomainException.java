package com.github.mehrdad.falahati.money.transfer.domain.exception;

import com.github.mehrdad.falahati.common.domain.exception.DomainException;

public class UserDomainException extends DomainException {
    public UserDomainException(String message) {
        super(message);
    }

    public UserDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
