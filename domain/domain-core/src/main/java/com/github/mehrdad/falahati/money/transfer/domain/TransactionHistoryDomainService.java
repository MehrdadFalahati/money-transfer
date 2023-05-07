package com.github.mehrdad.falahati.money.transfer.domain;

import com.github.mehrdad.falahati.money.transfer.domain.entity.TransactionHistory;
import com.github.mehrdad.falahati.money.transfer.domain.event.TransactionEvent;

public interface TransactionHistoryDomainService {
    TransactionEvent validateAndInitiateTransaction(TransactionHistory transactionHistory);
}
