package com.github.mehrdad.falahati.money.transfer.domain.dto.transaction;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public record TransactionResponse(
        @NotNull UUID transactionId,
        @NotNull String fromAccountNumber,
        @NotNull String toAccountNumber,
        @NotNull BigDecimal transferAmount,
        @NotNull Instant transactionDate
) {
}
