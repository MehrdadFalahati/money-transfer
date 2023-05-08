package com.github.mehrdad.falahati.money.transfer.domain.port.input.service;

import com.github.mehrdad.falahati.money.transfer.domain.dto.account.CreateAccountCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.account.CreateAccountResponse;
import com.github.mehrdad.falahati.money.transfer.domain.dto.user.CreateUserCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.user.CreateUserResponse;
import com.github.mehrdad.falahati.money.transfer.domain.entity.User;
import jakarta.validation.Valid;

import java.util.UUID;

public interface UserApplicationService {
    CreateUserResponse createUser(@Valid CreateUserCommand createUserCommand);
    CreateAccountResponse addAccountsToUser(UUID userId, @Valid CreateAccountCommand createAccountCommand);

    User getUserByUsername(String username);
}
