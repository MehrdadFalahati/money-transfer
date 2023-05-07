package com.github.mehrdad.falahati.money.transfer.domain.dto.transaction;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public record CreateTransactionCommand(
        @NotNull String fromAccountNumber,
        @NotNull String toAccountNumber,
        @NotNull BigDecimal amount
) {}
