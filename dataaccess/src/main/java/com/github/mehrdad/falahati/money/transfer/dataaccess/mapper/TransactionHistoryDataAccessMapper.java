package com.github.mehrdad.falahati.money.transfer.dataaccess.mapper;

import com.github.mehrdad.falahati.money.transfer.dataaccess.entity.TransactionHistoryEntity;
import com.github.mehrdad.falahati.money.transfer.domain.entity.TransactionHistory;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.Money;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.TransactionHistoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.time.ZoneOffset.UTC;

@Component
@RequiredArgsConstructor
public class TransactionHistoryDataAccessMapper {

    private final AccountDataAccessMapper accountDataAccessMapper;

    public TransactionHistory entityToTransactionHistory(TransactionHistoryEntity transactionHistoryEntity) {
        return TransactionHistory.builder()
                .id(new TransactionHistoryId(transactionHistoryEntity.getId()))
                .fromAccount(accountDataAccessMapper.entityToAccount(transactionHistoryEntity.getFromAccount()))
                .toAccount(accountDataAccessMapper.entityToAccount(transactionHistoryEntity.getToAccount()))
                .amount(new Money(transactionHistoryEntity.getAmount()))
                .createAt(transactionHistoryEntity.getCreateAt().atZone(UTC))
                .status(transactionHistoryEntity.getStatus())
                .build();
    }

    public TransactionHistoryEntity transactionHistoryToEntity(TransactionHistory transactionHistory) {
        return TransactionHistoryEntity.builder()
                .id(transactionHistory.getId().getValue())
                .amount(transactionHistory.getAmount().amount())
                .createAt(transactionHistory.getCreateAt().toInstant())
                .fromAccount(accountDataAccessMapper.accountToEntity(transactionHistory.getFromAccount()))
                .toAccount(accountDataAccessMapper.accountToEntity(transactionHistory.getToAccount()))
                .status(transactionHistory.getStatus())
                .build();
    }
}
