package com.github.mehrdad.falahati.money.transfer.domain.mapper;

import com.github.mehrdad.falahati.money.transfer.domain.dto.account.AccountInformation;
import com.github.mehrdad.falahati.money.transfer.domain.dto.account.CreateAccountCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.account.CreateAccountResponse;
import com.github.mehrdad.falahati.money.transfer.domain.dto.user.CreateUserCommand;
import com.github.mehrdad.falahati.money.transfer.domain.entity.Account;
import com.github.mehrdad.falahati.money.transfer.domain.entity.User;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.AccountId;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.Money;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.UserId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserDataMapper {

    public User createUserCommandToUser(CreateUserCommand createUserCommand) {
        return User.builder()
                .id(new UserId(UUID.randomUUID()))
                .firstName(createUserCommand.firstName())
                .lastName(createUserCommand.lastName())
                .username(createUserCommand.username())
                .password(createUserCommand.password())
                .phoneNumber(createUserCommand.phoneNumber())
                .build();
    }

    public Account accountInformationToAccount(AccountInformation accountInformation) {
        return Account.builder()
                .id(new AccountId(UUID.randomUUID()))
                .accountNumber(accountInformation.accountNumber())
                .currentBalance(new Money(accountInformation.currentBalance()))
                .build();
    }

    public CreateAccountResponse userToAccountResponse(User user) {
        List<String> accounts = user.getAccounts().stream()
                .map(Account::getAccountNumber)
                .toList();
        return new CreateAccountResponse(user.getId().getValue(), accounts);
    }
}
