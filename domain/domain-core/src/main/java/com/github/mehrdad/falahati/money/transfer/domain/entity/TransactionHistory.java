package com.github.mehrdad.falahati.money.transfer.domain.entity;

import com.github.mehrdad.falahati.common.domain.entity.AggregateRoot;
import com.github.mehrdad.falahati.money.transfer.domain.exception.TransactionException;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.Money;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.TransactionHistoryId;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.TransactionStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class TransactionHistory extends AggregateRoot<TransactionHistoryId> {

    public static final String UTC = "UTC";
    private final Account withdrawAccount;
    private final Account depositAccount;
    private final Money amount;
    private TransactionStatus status;
    private ZonedDateTime createAt;

    public void initializeTransactionHistory() {
        setId(new TransactionHistoryId(UUID.randomUUID()));
        createAt = ZonedDateTime.now(ZoneId.of(UTC));
    }

    public void validateTransactionHistory() {
        if (amount == null || !amount.isGreaterThanZero())
            throw new TransactionException("Amount must be greater than zero!");
        if (!withdrawAccount.getCurrentBalance().isGreaterThan(amount)) {
            throw new TransactionException("Account doesn't have enough money");
        }
    }

    public void withdraw() {
        withdrawAccount.updateCurrentBalance(withdrawAccount.getCurrentBalance().subtract(amount));
    }

    public void deposit() {
        depositAccount.updateCurrentBalance(depositAccount.getCurrentBalance().add(amount));
    }

    public void updateStatus(TransactionStatus status) {
        this.status = status;
    }

    private TransactionHistory(Builder builder) {
        super.setId(builder.id);
        withdrawAccount = builder.withdrawAccount;
        depositAccount = builder.depositAccount;
        amount = builder.amount;
        status = builder.status;
        createAt = builder.createAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Account getWithdrawAccount() {
        return withdrawAccount;
    }

    public Account getDepositAccount() {
        return depositAccount;
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
        private Account withdrawAccount;
        private Account depositAccount;
        private Money amount;
        private TransactionStatus status;
        private ZonedDateTime createAt;

        private Builder() {
        }

        public Builder id(TransactionHistoryId val) {
            id = val;
            return this;
        }

        public Builder withdrawAccount(Account val) {
            withdrawAccount = val;
            return this;
        }

        public Builder depositAccount(Account val) {
            depositAccount = val;
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
