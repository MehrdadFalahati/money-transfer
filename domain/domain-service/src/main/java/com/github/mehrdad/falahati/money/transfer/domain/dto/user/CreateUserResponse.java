package com.github.mehrdad.falahati.money.transfer.domain.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateUserResponse(@NotNull UUID userId) {
}
