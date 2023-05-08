package com.github.mehrdad.falahati.money.transfer.application.rest;

import com.github.mehrdad.falahati.money.transfer.application.rest.dto.LoginRequest;
import com.github.mehrdad.falahati.money.transfer.application.rest.dto.LoginResponse;
import com.github.mehrdad.falahati.money.transfer.domain.dto.account.CreateAccountCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.account.CreateAccountResponse;
import com.github.mehrdad.falahati.money.transfer.domain.dto.user.CreateUserCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.user.CreateUserResponse;
import com.github.mehrdad.falahati.money.transfer.domain.entity.User;
import com.github.mehrdad.falahati.money.transfer.domain.exception.UserDomainException;
import com.github.mehrdad.falahati.money.transfer.domain.port.input.service.UserApplicationService;
import com.github.mehrdad.falahati.money.transfer.security.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userApplicationService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil tokenUtil;

    @PostMapping
    public ResponseEntity<CreateUserResponse> register(@RequestBody CreateUserCommand createUserCommand) {
        log.info("Creating user with username = {}", createUserCommand.getUsername());
        createUserCommand.setPassword(passwordEncoder.encode(createUserCommand.getPassword()));
        CreateUserResponse createUserResponse = userApplicationService.createUser(createUserCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserResponse);
    }

    @PutMapping("/{id}/accounts")
    @SecurityRequirement(name = "security")
    @PreAuthorize("hasAnyAuthority('account:write')")
    public ResponseEntity<CreateAccountResponse> addAccounts(@PathVariable String id, @RequestBody CreateAccountCommand createAccountCommand) {
        log.info("Adding account to User[userId={}]", id);
        CreateAccountResponse createAccountResponse = userApplicationService.addAccountsToUser(UUID.fromString(id), createAccountCommand);
        return ResponseEntity.ok(createAccountResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        authenticate(loginRequest.username(), loginRequest.password());

        User result = userApplicationService.getUserByUsername(loginRequest.username());
        return ResponseEntity.ok(
                LoginResponse.builder()
                        .userId(result.getId().getValue().toString())
                        .token(tokenUtil.generateAccessToken(result))
                        .build());
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new UserDomainException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new UserDomainException("INVALID_CREDENTIALS", e);
        }
    }
}
