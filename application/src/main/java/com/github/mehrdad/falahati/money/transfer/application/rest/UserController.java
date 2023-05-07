package com.github.mehrdad.falahati.money.transfer.application.rest;

import com.github.mehrdad.falahati.money.transfer.domain.dto.account.CreateAccountCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.account.CreateAccountResponse;
import com.github.mehrdad.falahati.money.transfer.domain.dto.user.CreateUserCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.user.CreateUserResponse;
import com.github.mehrdad.falahati.money.transfer.domain.port.input.service.UserApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userApplicationService;

    @PostMapping
    public ResponseEntity<CreateUserResponse> register(@RequestBody CreateUserCommand createUserCommand) {
        log.info("Creating user with username = {}", createUserCommand.username());
        CreateUserResponse createUserResponse = userApplicationService.createUser(createUserCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateAccountResponse> addAccounts(@PathVariable String id, @RequestBody CreateAccountCommand createAccountCommand) {
        log.info("Adding account to User[userId={}]", id);
        CreateAccountResponse createAccountResponse = userApplicationService.addAccountsToUser(UUID.fromString(id), createAccountCommand);
        return ResponseEntity.ok(createAccountResponse);
    }
}
