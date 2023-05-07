package com.github.mehrdad.falahati.money.transfer.domain.port.input.service;

import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.CreateTransactionCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.CreateTransactionResponse;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.ReportTransactionQuery;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.ReportTransactionResponse;
import jakarta.validation.Valid;

public interface TransactionHistoryApplicationService {
    CreateTransactionResponse createTransaction(@Valid CreateTransactionCommand createTransactionCommand);
    ReportTransactionResponse reportTransactions(@Valid ReportTransactionQuery reportTransactionQuery);
}
