package com.github.mehrdad.falahati.money.transfer.domain;

import com.github.mehrdad.falahati.money.transfer.domain.port.output.repository.AccountRepository;
import com.github.mehrdad.falahati.money.transfer.domain.port.output.repository.TransactionHistoryRepository;
import com.github.mehrdad.falahati.money.transfer.domain.port.output.repository.UserRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.github.mehrdad.falahati")
public class TestConfiguration {

    @Bean
    public UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    public AccountRepository accountRepository() {
        return Mockito.mock(AccountRepository.class);
    }

    @Bean
    public TransactionHistoryRepository transactionHistoryRepository() {
        return Mockito.mock(TransactionHistoryRepository.class);
    }

    @Bean
    public TransactionHistoryDomainService transactionHistoryDomainService() {
        return new TransactionHistoryDomainServiceImpl();
    }
}
