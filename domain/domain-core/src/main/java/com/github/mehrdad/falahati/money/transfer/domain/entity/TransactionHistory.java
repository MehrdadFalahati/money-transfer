package com.github.mehrdad.falahati.money.transfer.domain.entity;

import com.github.mehrdad.falahati.common.domain.entity.AggregateRoot;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.Money;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.TransactionHistoryId;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.TransactionStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class TransactionHistory extends AggregateRoot<TransactionHistoryId> {

    public static final String UTC = "UTC";
    private final Account fromAccount;
    private final Account toAccount;
    private final Money amount;
    private TransactionStatus status;
    private ZonedDateTime createAt;

    public void initializeTransactionHistory() {
        setId(new TransactionHistoryId(UUID.randomUUID()));
        createAt = ZonedDateTime.now(ZoneId.of(UTC));
    }

    public void validateTransactionHistory(List<String> failureMessages) {
        if (amount == null || !amount.isGreaterThanZero())
            failureMessages.add("Amount must be greater than zero!");
        if (!fromAccount.getCurrentBalance().isGreaterThan(amount)) {
            failureMessages.add("Account doesn't have enough money");
        }
    }

    public void withdraw() {
        fromAccount.updateCurrentBalance(fromAccount.getCurrentBalance().subtract(amount));
    }

    public void withdrawFailed() {
        fromAccount.updateCurrentBalance(fromAccount.getCurrentBalance().add(amount));
    }

    public void deposit() {
        toAccount.updateCurrentBalance(toAccount.getCurrentBalance().add(amount));
    }

    public void depositFailed() {
        toAccount.updateCurrentBalance(toAccount.getCurrentBalance().subtract(amount));
    }

    public void updateStatus(TransactionStatus status) {
        this.status = status;
    }

    private TransactionHistory(Builder builder) {
        super.setId(builder.id);
        fromAccount = builder.fromAccount;
        toAccount = builder.toAccount;
        amount = builder.amount;
        status = builder.status;
        createAt = builder.createAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public Money getAmount() {
        return amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public ZonedDateTime getCreateAt() {
        return createAt;
    }

    public static final class Builder {
        private TransactionHistoryId id;
        private Account fromAccount;
        private Account toAccount;
        private Money amount;
        private TransactionStatus status;
        private ZonedDateTime createAt;

        private Builder() {
        }

        public Builder id(TransactionHistoryId val) {
            id = val;
            return this;
        }

        public Builder fromAccount(Account val) {
            fromAccount = val;
            return this;
        }

        public Builder toAccount(Account val) {
            toAccount = val;
            return this;
        }

        public Builder amount(Money val) {
            amount = val;
            return this;
        }

        public Builder status(TransactionStatus val) {
            status = val;
            return this;
        }

        public Builder createAt(ZonedDateTime val) {
            createAt = val;
            return this;
        }

        public TransactionHistory build() {
            return new TransactionHistory(this);
        }
    }
}
