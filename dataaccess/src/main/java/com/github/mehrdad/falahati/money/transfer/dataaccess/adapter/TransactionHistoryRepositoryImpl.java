package com.github.mehrdad.falahati.money.transfer.dataaccess.adapter;

import com.github.mehrdad.falahati.money.transfer.dataaccess.mapper.TransactionHistoryDataAccessMapper;
import com.github.mehrdad.falahati.money.transfer.dataaccess.repository.TransactionHistoryJpaRepository;
import com.github.mehrdad.falahati.money.transfer.domain.entity.TransactionHistory;
import com.github.mehrdad.falahati.money.transfer.domain.port.output.repository.TransactionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionHistoryRepositoryImpl implements TransactionHistoryRepository {

    private final TransactionHistoryJpaRepository transactionHistoryJpaRepository;
    private final TransactionHistoryDataAccessMapper transactionHistoryDataAccessMapper;
    @Override
    public TransactionHistory save(TransactionHistory transactionHistory) {
        return transactionHistoryDataAccessMapper
                .entityToTransactionHistory(transactionHistoryJpaRepository.save(transactionHistoryDataAccessMapper.transactionHistoryToEntity(transactionHistory)));
    }

    @Override
    public List<TransactionHistory> findByAccountFromAccountNumberAndTransactionDateBetween(String accountNumber, Instant fromDate, Instant toDate) {
        return transactionHistoryJpaRepository.findAllByFromAccount_AccountNumberAndCreateAtBetween(accountNumber, fromDate, toDate).stream()
                .map(transactionHistoryDataAccessMapper::entityToTransactionHistory)
                .toList();
    }
}
