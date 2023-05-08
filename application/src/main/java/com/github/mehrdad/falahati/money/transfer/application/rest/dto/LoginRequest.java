package com.github.mehrdad.falahati.money.transfer.application.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LoginRequest(@NotNull String username, @NotNull String password) {
}
