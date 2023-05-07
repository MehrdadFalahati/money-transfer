package com.github.mehrdad.falahati.money.transfer.domain.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public record CreateUserResponse(@NotNull UUID userId) {
}
