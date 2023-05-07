package com.github.mehrdad.falahati.money.transfer.domain.event;

import com.github.mehrdad.falahati.money.transfer.domain.entity.TransactionHistory;

import java.time.ZonedDateTime;
import java.util.List;

public class TransactionFailEvent extends TransactionEvent {
    public TransactionFailEvent(TransactionHistory transactionHistory, ZonedDateTime createAt, List<String> failureMessages) {
        super(transactionHistory, createAt, failureMessages);
    }
}
