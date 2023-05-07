package com.github.mehrdad.falahati.money.transfer.container;

import com.github.mehrdad.falahati.money.transfer.domain.TransactionHistoryDomainService;
import com.github.mehrdad.falahati.money.transfer.domain.TransactionHistoryDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public TransactionHistoryDomainService transactionHistoryDomainService() {
        return new TransactionHistoryDomainServiceImpl();
    }
}
