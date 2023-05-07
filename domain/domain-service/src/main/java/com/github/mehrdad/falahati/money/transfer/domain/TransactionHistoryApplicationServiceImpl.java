package com.github.mehrdad.falahati.money.transfer.domain;

import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.CreateTransactionCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.CreateTransactionResponse;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.ReportTransactionQuery;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.ReportTransactionResponse;
import com.github.mehrdad.falahati.money.transfer.domain.entity.Account;
import com.github.mehrdad.falahati.money.transfer.domain.entity.TransactionHistory;
import com.github.mehrdad.falahati.money.transfer.domain.entity.User;
import com.github.mehrdad.falahati.money.transfer.domain.event.TransactionEvent;
import com.github.mehrdad.falahati.money.transfer.domain.exception.TransactionException;
import com.github.mehrdad.falahati.money.transfer.domain.exception.UserDomainException;
import com.github.mehrdad.falahati.money.transfer.domain.mapper.TransactionHistoryDataMapper;
import com.github.mehrdad.falahati.money.transfer.domain.port.input.service.TransactionHistoryApplicationService;
import com.github.mehrdad.falahati.money.transfer.domain.port.output.repository.TransactionHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class TransactionHistoryApplicationServiceImpl implements TransactionHistoryApplicationService {
    private final TransactionHistoryDataMapper transactionHistoryDataMapper;
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final AccountService accountService;
    private final TransactionHistoryDomainService transactionHistoryDomainService;

    @Override
    @Transactional(noRollbackFor = TransactionException.class)
    public CreateTransactionResponse createTransaction(CreateTransactionCommand createTransactionCommand) {
        Account fromAccount = accountService.accountByAccountNumber(createTransactionCommand.fromAccountNumber());
        Account toAccount = accountService.accountByAccountNumber(createTransactionCommand.toAccountNumber());
        TransactionHistory transactionHistory = transactionHistoryDataMapper.createTransactionToTransactionHistory(createTransactionCommand, fromAccount, toAccount);
        TransactionEvent transactionEvent = transactionHistoryDomainService.validateAndInitiateTransaction(transactionHistory);
        TransactionHistory result = saveUser(transactionHistory);
        log.info("Transaction is created with id: {}", transactionEvent.getTransactionHistory().getId().getValue());
        if (!transactionEvent.getFailureMessages().isEmpty())
            throw new TransactionException(String.join(",", transactionEvent.getFailureMessages()));
        return transactionHistoryDataMapper.transactionHistoryToCreateTransactionResponse(result);
    }

    @Override
    @Transactional(readOnly = true)
    public ReportTransactionResponse reportTransactions(ReportTransactionQuery reportTransactionQuery) {
        List<TransactionHistory> transactionHistories = transactionHistoryRepository.findByAccountFromAccountNumberAndTransactionDateBetween(reportTransactionQuery.accountNumber(),
                reportTransactionQuery.fromDate(), reportTransactionQuery.toDate());
        return transactionHistoryDataMapper.transactionHistoriesToReportTransactionResponse(transactionHistories);
    }

    private TransactionHistory saveUser(TransactionHistory transactionHistory) {
        TransactionHistory result = transactionHistoryRepository.save(transactionHistory);
        if (result == null) {
            log.error("Could not save transaction history!");
            throw new UserDomainException("Could not save transaction history!");
        }
        log.info("TransactionHistory[id={}] inserted to DB", result.getId().getValue());
        return result;
    }
}
