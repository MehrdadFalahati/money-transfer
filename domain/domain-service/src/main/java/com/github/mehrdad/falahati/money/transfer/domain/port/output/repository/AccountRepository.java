package com.github.mehrdad.falahati.money.transfer.domain.port.output.repository;

import com.github.mehrdad.falahati.money.transfer.domain.entity.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findByAccountNumber(String accountNumber);
}
