package com.github.mehrdad.falahati.money.transfer.domain.valueobject;

import com.github.mehrdad.falahati.common.domain.valueobject.BaseId;

import java.util.UUID;

public class UserId extends BaseId<UUID> {
    public UserId(UUID value) {
        super(value);
    }
}
