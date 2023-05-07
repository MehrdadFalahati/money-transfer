package com.github.mehrdad.falahati.money.transfer.dataaccess.repository;

import com.github.mehrdad.falahati.money.transfer.dataaccess.entity.TransactionHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionHistoryJpaRepository extends JpaRepository<TransactionHistoryEntity, UUID> {
    List<TransactionHistoryEntity> findAllByFromAccount_AccountNumberAndCreateAtBetween(String withdrawAccount, Instant fromDate, Instant toDate);
}
