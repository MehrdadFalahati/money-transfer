package com.github.mehrdad.falahati.money.transfer.domain.port.input.service;

import com.github.mehrdad.falahati.money.transfer.domain.dto.account.CreateAccountCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.account.CreateAccountResponse;
import com.github.mehrdad.falahati.money.transfer.domain.dto.user.CreateUserCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.user.CreateUserResponse;

public interface UserApplicationService {
    CreateUserResponse createUser(CreateUserCommand createUserCommand);
    CreateAccountResponse addAccountsToUser(CreateAccountCommand createAccountCommand);
}
