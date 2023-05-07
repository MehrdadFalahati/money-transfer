package com.github.mehrdad.falahati.money.transfer.domain;

import com.github.mehrdad.falahati.money.transfer.domain.entity.Account;
import com.github.mehrdad.falahati.money.transfer.domain.exception.NotFoundException;
import com.github.mehrdad.falahati.money.transfer.domain.port.output.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Account accountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> {
                    log.warn("Can not found Account[number={}]", accountNumber);
                    return new NotFoundException("Can not found Account[number=" + accountNumber + "]!");
                });
    }
}
