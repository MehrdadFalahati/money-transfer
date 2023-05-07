package com.github.mehrdad.falahati.money.transfer.dataaccess.repository;

import com.github.mehrdad.falahati.money.transfer.dataaccess.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
