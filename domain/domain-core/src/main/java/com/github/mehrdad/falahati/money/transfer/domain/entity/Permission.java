package com.github.mehrdad.falahati.money.transfer.domain.entity;

public enum Permission {
    TRANSACTION_READ("transaction:read"),
    TRANSACTION_WRITE("transaction:write"),
    ACCOUNT_WRITE("account:write");

    private final String permissionName;

    Permission(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionName() {
        return permissionName;
    }
}
