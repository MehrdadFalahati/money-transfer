package com.github.mehrdad.falahati.money.transfer.domain;

import com.github.mehrdad.falahati.money.transfer.domain.entity.TransactionHistory;
import com.github.mehrdad.falahati.money.transfer.domain.event.TransactionEvent;
import com.github.mehrdad.falahati.money.transfer.domain.event.TransactionFailEvent;
import com.github.mehrdad.falahati.money.transfer.domain.event.TransactionSuccessEvent;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.TransactionStatus;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import static com.github.mehrdad.falahati.money.transfer.domain.entity.TransactionHistory.UTC;

@Slf4j
public class TransactionHistoryDomainServiceImpl implements TransactionHistoryDomainService {
    @Override
    public TransactionEvent validateAndInitiateTransaction(TransactionHistory transactionHistory) {
        ArrayList<String> failureMessages = new ArrayList<>();
        transactionHistory.validateTransactionHistory(failureMessages);
        transactionHistory.initializeTransactionHistory();
        transactionHistory.withdraw();
        transactionHistory.deposit();
        if (failureMessages.isEmpty()) {
            log.info("Transfer money between AccountNumber=[{}] to AccountNumber=[{}] is successful",
                    transactionHistory.getFromAccount().getAccountNumber(), transactionHistory.getToAccount().getAccountNumber());
            transactionHistory.updateStatus(TransactionStatus.SUCCESS);
            return new TransactionSuccessEvent(transactionHistory, ZonedDateTime.now(ZoneId.of(UTC)));
        }

        log.info("Transfer money between AccountNumber=[{}] to AccountNumber=[{}] is failed",
                transactionHistory.getFromAccount().getAccountNumber(), transactionHistory.getToAccount().getAccountNumber());
        transactionHistory.updateStatus(TransactionStatus.FAILED);
        transactionHistory.withdrawFailed();
        transactionHistory.depositFailed();
        return new TransactionFailEvent(transactionHistory, ZonedDateTime.now(ZoneId.of(UTC)), failureMessages);
    }
}
