package com.github.mehrdad.falahati.money.transfer.domain.exception;

import com.github.mehrdad.falahati.common.domain.exception.DomainException;

public class TransactionException extends DomainException {
    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
