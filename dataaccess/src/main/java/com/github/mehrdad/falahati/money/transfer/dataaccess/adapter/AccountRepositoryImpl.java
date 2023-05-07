package com.github.mehrdad.falahati.money.transfer.dataaccess.adapter;

import com.github.mehrdad.falahati.money.transfer.dataaccess.mapper.AccountDataAccessMapper;
import com.github.mehrdad.falahati.money.transfer.dataaccess.repository.AccountJpaRepository;
import com.github.mehrdad.falahati.money.transfer.domain.entity.Account;
import com.github.mehrdad.falahati.money.transfer.domain.port.output.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;
    private final AccountDataAccessMapper accountDataAccessMapper;
    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return accountJpaRepository.findByAccountNumber(accountNumber).map(accountDataAccessMapper::entityToAccount);
    }
}
