package com.github.mehrdad.falahati.money.transfer.domain.port.output.repository;

import com.github.mehrdad.falahati.money.transfer.domain.entity.TransactionHistory;

import java.time.Instant;
import java.util.List;

public interface TransactionHistoryRepository {
    TransactionHistory save(TransactionHistory transactionHistory);
    List<TransactionHistory> findByAccountFromAccountNumberAndTransactionDateBetween(String accountNumber, Instant fromDate, Instant toDate);
}
