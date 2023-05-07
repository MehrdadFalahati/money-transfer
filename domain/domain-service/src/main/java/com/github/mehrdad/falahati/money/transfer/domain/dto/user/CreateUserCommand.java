package com.github.mehrdad.falahati.money.transfer.domain.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateUserCommand(
        @NotNull String username,
        @NotNull String password,
        @NotNull String firstName,
        @NotNull String lastName,
        @NotNull String phoneNumber) {
}
