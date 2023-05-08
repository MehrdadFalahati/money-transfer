package com.github.mehrdad.falahati.money.transfer.domain.entity;


import java.util.Set;
import java.util.stream.Collectors;

import static com.github.mehrdad.falahati.money.transfer.domain.entity.Permission.*;


public enum Role {
    CUSTOMER(Set.of(TRANSACTION_READ, TRANSACTION_WRITE, ACCOUNT_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
