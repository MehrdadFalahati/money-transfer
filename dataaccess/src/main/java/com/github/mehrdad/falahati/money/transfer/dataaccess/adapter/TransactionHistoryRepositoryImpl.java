package com.github.mehrdad.falahati.money.transfer.dataaccess.adapter;

import com.github.mehrdad.falahati.money.transfer.domain.entity.TransactionHistory;
import com.github.mehrdad.falahati.money.transfer.domain.port.output.repository.TransactionHistoryRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class TransactionHistoryRepositoryImpl implements TransactionHistoryRepository {
    @Override
    public TransactionHistory save(TransactionHistory transactionHistory) {
        return null;
    }

    @Override
    public List<TransactionHistory> findByAccountFromAccountNumberAndTransactionDateBetween(String accountNumber, Instant fromDate, Instant toDate) {
        return null;
    }
}
