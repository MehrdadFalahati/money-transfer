package com.github.mehrdad.falahati.money.transfer.domain.event;

import com.github.mehrdad.falahati.money.transfer.domain.entity.TransactionHistory;

import java.time.ZonedDateTime;
import java.util.Collections;

public class TransactionSuccessEvent extends TransactionEvent {
    public TransactionSuccessEvent(TransactionHistory transactionHistory, ZonedDateTime createAt) {
        super(transactionHistory, createAt, Collections.emptyList());
    }
}
