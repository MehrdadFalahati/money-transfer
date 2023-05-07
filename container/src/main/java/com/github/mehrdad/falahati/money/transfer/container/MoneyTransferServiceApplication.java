package com.github.mehrdad.falahati.money.transfer.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.github.mehrdad.falahati.money.transfer.dataaccess")
@EnableJpaRepositories(basePackages = "com.github.mehrdad.falahati.money.transfer.dataaccess")
@SpringBootApplication(scanBasePackages = "com.github.mehrdad.falahati")
public class MoneyTransferServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoneyTransferServiceApplication.class, args);
    }
}
