package com.github.mehrdad.falahati.money.transfer.domain.mapper;

import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.CreateTransactionCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.CreateTransactionResponse;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.ReportTransactionResponse;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.TransactionResponse;
import com.github.mehrdad.falahati.money.transfer.domain.entity.Account;
import com.github.mehrdad.falahati.money.transfer.domain.entity.TransactionHistory;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.Money;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionHistoryDataMapper {

    public TransactionHistory createTransactionToTransactionHistory(CreateTransactionCommand createTransactionCommand, Account from, Account to) {
        return TransactionHistory.builder()
                .fromAccount(from)
                .toAccount(to)
                .amount(new Money(createTransactionCommand.amount()))
                .build();
    }

    public CreateTransactionResponse transactionHistoryToCreateTransactionResponse(TransactionHistory transactionHistory) {
        return CreateTransactionResponse.builder()
                .information(createTransactionResponse(transactionHistory))
                .build();
    }

    public ReportTransactionResponse transactionHistoriesToReportTransactionResponse(List<TransactionHistory> transactionHistories) {
        List<TransactionResponse> transactionResponses = transactionHistories.stream()
                .map(this::createTransactionResponse)
                .toList();

        return ReportTransactionResponse.builder()
                .transactions(transactionResponses)
                .build();
    }

    private TransactionResponse createTransactionResponse(TransactionHistory transactionHistory) {
        return TransactionResponse.builder()
                .transactionId(transactionHistory.getId().getValue())
                .fromAccountNumber(transactionHistory.getFromAccount().getAccountNumber())
                .toAccountNumber(transactionHistory.getToAccount().getAccountNumber())
                .transferAmount(transactionHistory.getAmount().amount())
                .transactionDate(transactionHistory.getCreateAt().toInstant())
                .status(transactionHistory.getStatus())
                .build();
    }
}
