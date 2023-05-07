package com.github.mehrdad.falahati.money.transfer.domain.event;

import com.github.mehrdad.falahati.common.domain.event.DomainEvent;
import com.github.mehrdad.falahati.money.transfer.domain.entity.TransactionHistory;

import java.time.ZonedDateTime;
import java.util.List;

public abstract class TransactionEvent implements DomainEvent<TransactionHistory> {
    private final TransactionHistory transactionHistory;
    private final ZonedDateTime createAt;

    private final List<String> failureMessages;

    public TransactionEvent(TransactionHistory transactionHistory, ZonedDateTime createAt, List<String> failureMessages) {
        this.transactionHistory = transactionHistory;
        this.createAt = createAt;
        this.failureMessages = failureMessages;
    }

    public TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }

    public ZonedDateTime getCreateAt() {
        return createAt;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }
}
