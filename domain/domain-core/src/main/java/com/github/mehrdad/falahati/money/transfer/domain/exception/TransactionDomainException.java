package com.github.mehrdad.falahati.money.transfer.domain.exception;

import com.github.mehrdad.falahati.common.domain.exception.DomainException;

public class TransactionDomainException extends DomainException {
    public TransactionDomainException(String message) {
        super(message);
    }

    public TransactionDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
