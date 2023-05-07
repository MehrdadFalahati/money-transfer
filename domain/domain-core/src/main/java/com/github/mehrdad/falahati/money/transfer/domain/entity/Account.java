package com.github.mehrdad.falahati.money.transfer.domain.entity;

import com.github.mehrdad.falahati.common.domain.entity.BaseEntity;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.AccountId;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.Money;

public class Account extends BaseEntity<AccountId> {
    private final String accountNumber;
    private final Money currentBalance;

    private Account(Builder builder) {
        super.setId(builder.id);
        accountNumber = builder.accountNumber;
        currentBalance = builder.currentBalance;
    }

    public static Builder builder() {
        return new Builder();
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public Money getCurrentBalance() {
        return currentBalance;
    }

    public static final class Builder {
        private AccountId id;
        private String accountNumber;
        private Money currentBalance;

        private Builder() {
        }

        public Builder id(AccountId val) {
            id = val;
            return this;
        }

        public Builder accountNumber(String val) {
            accountNumber = val;
            return this;
        }

        public Builder currentBalance(Money val) {
            currentBalance = val;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}
