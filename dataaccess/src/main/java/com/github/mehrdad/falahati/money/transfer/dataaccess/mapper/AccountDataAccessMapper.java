package com.github.mehrdad.falahati.money.transfer.dataaccess.mapper;

import com.github.mehrdad.falahati.money.transfer.dataaccess.entity.AccountEntity;
import com.github.mehrdad.falahati.money.transfer.domain.entity.Account;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.AccountId;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.Money;
import org.springframework.stereotype.Component;

@Component
public class AccountDataAccessMapper {

    public Account entityToAccount(AccountEntity account) {
        return Account.builder()
                .id(new AccountId(account.getId()))
                .accountNumber(account.getAccountNumber())
                .currentBalance(new Money(account.getCurrentBalance()))
                .build();
    }

    public AccountEntity accountToEntity(Account account) {
        return AccountEntity.builder()
                .id(account.getId().getValue())
                .accountNumber(account.getAccountNumber())
                .currentBalance(account.getCurrentBalance().amount())
                .build();
    }
}
