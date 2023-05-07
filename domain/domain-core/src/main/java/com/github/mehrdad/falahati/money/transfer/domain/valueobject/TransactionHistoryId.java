package com.github.mehrdad.falahati.money.transfer.domain.valueobject;

import com.github.mehrdad.falahati.common.domain.valueobject.BaseId;

import java.util.UUID;

public class TransactionHistoryId extends BaseId<UUID> {
    public TransactionHistoryId(UUID value) {
        super(value);
    }
}
