package com.github.mehrdad.falahati.money.transfer.application.rest;

import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.CreateTransactionCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.CreateTransactionResponse;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.ReportTransactionQuery;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.ReportTransactionResponse;
import com.github.mehrdad.falahati.money.transfer.domain.port.input.service.TransactionHistoryApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionHistoryApplicationService transactionHistoryApplicationService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('transaction:write')")
    public ResponseEntity<CreateTransactionResponse> createTransaction(@RequestBody CreateTransactionCommand createTransactionCommand) {
        log.info("Transfer money between Account[accountNumber={}] to Account[accountNumber={}]",
                createTransactionCommand.fromAccountNumber(), createTransactionCommand.toAccountNumber());
        CreateTransactionResponse createTransactionResponse = transactionHistoryApplicationService.createTransaction(createTransactionCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(createTransactionResponse);
    }

    @GetMapping("/reports")
    @PreAuthorize("hasAnyAuthority('transaction:read')")
    public ResponseEntity<ReportTransactionResponse> reports(ReportTransactionQuery reportTransactionQuery) {
        log.info("Transaction report for Account[accountNumber={}]", reportTransactionQuery.accountNumber());
        ReportTransactionResponse reportTransactionResponse = transactionHistoryApplicationService.reportTransactions(reportTransactionQuery);
        return ResponseEntity.ok(reportTransactionResponse);
    }
}
