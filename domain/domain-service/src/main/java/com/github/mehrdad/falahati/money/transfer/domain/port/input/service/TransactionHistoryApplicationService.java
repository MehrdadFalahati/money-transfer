package com.github.mehrdad.falahati.money.transfer.domain.port.input.service;

import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.CreateTransactionCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.CreateTransactionResponse;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.ReportTransactionQuery;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.ReportTransactionResponse;

public interface TransactionHistoryApplicationService {
    CreateTransactionResponse createTransaction(CreateTransactionCommand createTransactionCommand);
    ReportTransactionResponse reportTransactions(ReportTransactionQuery reportTransactionQuery);
}
