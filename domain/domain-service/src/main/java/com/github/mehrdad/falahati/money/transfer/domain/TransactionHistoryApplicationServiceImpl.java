package com.github.mehrdad.falahati.money.transfer.domain;

import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.CreateTransactionCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.CreateTransactionResponse;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.ReportTransactionQuery;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.ReportTransactionResponse;
import com.github.mehrdad.falahati.money.transfer.domain.port.input.service.TransactionHistoryApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class TransactionHistoryApplicationServiceImpl implements TransactionHistoryApplicationService {
    @Override
    public CreateTransactionResponse createTransaction(CreateTransactionCommand createTransactionCommand) {
        return null;
    }

    @Override
    public ReportTransactionResponse reportTransactions(ReportTransactionQuery reportTransactionQuery) {
        return null;
    }
}
