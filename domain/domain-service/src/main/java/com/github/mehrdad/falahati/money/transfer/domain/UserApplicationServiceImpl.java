package com.github.mehrdad.falahati.money.transfer.domain;

import com.github.mehrdad.falahati.money.transfer.domain.dto.account.CreateAccountCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.account.CreateAccountResponse;
import com.github.mehrdad.falahati.money.transfer.domain.dto.user.CreateUserCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.user.CreateUserResponse;
import com.github.mehrdad.falahati.money.transfer.domain.entity.User;
import com.github.mehrdad.falahati.money.transfer.domain.exception.NotFoundException;
import com.github.mehrdad.falahati.money.transfer.domain.exception.UserDomainException;
import com.github.mehrdad.falahati.money.transfer.domain.mapper.UserDataMapper;
import com.github.mehrdad.falahati.money.transfer.domain.port.input.service.UserApplicationService;
import com.github.mehrdad.falahati.money.transfer.domain.port.output.repository.UserRepository;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserDataMapper userDataMapper;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserCommand createUserCommand) {
        User user = userDataMapper.createUserCommandToUser(createUserCommand);
        User result = saveUser(user, "inserted to DB");
        return new CreateUserResponse(result.getId().getValue());
    }

    @Override
    @Transactional
    public CreateAccountResponse addAccountsToUser(UUID userId, CreateAccountCommand createAccountCommand) {
        User user = userRepository.findById(new UserId(userId))
                .orElseThrow(() -> new NotFoundException("User[userId=" + userId.toString() + "] not found!"));
        createAccountCommand.accounts().stream()
                .map(userDataMapper::accountInformationToAccount)
                .forEach(user::addAccounts);
        User result = saveUser(user, "inserted accounts");
        return userDataMapper.userToAccountResponse(result);
    }

    private User saveUser(User user, String message) {
        User result = userRepository.save(user);
        if (result == null) {
            log.error("Could not save user!");
            throw new UserDomainException("Could not save user!");
        }
        log.info("User[username={}] {}", result.getUsername(), message);
        return result;
    }
}
