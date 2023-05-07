package com.github.mehrdad.falahati.money.transfer.domain.dto.account;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record CreateAccountResponse(@NotNull UUID userId, @NotNull List<String> accountNumbers) {
}
