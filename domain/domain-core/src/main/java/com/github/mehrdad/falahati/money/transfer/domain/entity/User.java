package com.github.mehrdad.falahati.money.transfer.domain.entity;

import com.github.mehrdad.falahati.common.domain.entity.BaseEntity;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.UserId;

import java.util.HashSet;
import java.util.Set;

public class User extends BaseEntity<UserId> {
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String phoneNumber;
    private final Set<Account> accounts;

    private User(Builder builder) {
        super.setId(builder.id);
        firstName = builder.firstName;
        lastName = builder.lastName;
        username = builder.username;
        password = builder.password;
        phoneNumber = builder.phoneNumber;
        accounts = new HashSet<>();
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void addAccounts(Account account) {
        accounts.add(account);
    }


    public static final class Builder {
        private UserId id;
        private String firstName;
        private String lastName;
        private String username;
        private String password;
        private String phoneNumber;
        private Builder() {
        }

        public Builder id(UserId val) {
            id = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder phoneNumber(String val) {
            phoneNumber = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
