package com.github.mehrdad.falahati.money.transfer.dataaccess.adapter;

import com.github.mehrdad.falahati.money.transfer.domain.entity.Account;
import com.github.mehrdad.falahati.money.transfer.domain.port.output.repository.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return Optional.empty();
    }
}
