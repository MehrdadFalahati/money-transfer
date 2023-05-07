package com.github.mehrdad.falahati.money.transfer.domain.valueobject;

import com.github.mehrdad.falahati.common.domain.valueobject.BaseId;

import java.util.UUID;

public class AccountId extends BaseId<UUID> {
    public AccountId(UUID value) {
        super(value);
    }
}
