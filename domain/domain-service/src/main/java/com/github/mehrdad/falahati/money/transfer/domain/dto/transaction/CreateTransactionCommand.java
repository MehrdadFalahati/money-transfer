package com.github.mehrdad.falahati.money.transfer.domain.dto.transaction;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CreateTransactionCommand(
        @NotNull String fromAccountNumber,
        @NotNull String toAccountNumber,
        @NotNull BigDecimal amount
) {}
