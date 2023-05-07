package com.github.mehrdad.falahati.money.transfer.domain.dto.transaction;

import com.github.mehrdad.falahati.money.transfer.domain.valueobject.TransactionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
public record TransactionResponse(
        @NotNull UUID transactionId,
        @NotNull String fromAccountNumber,
        @NotNull String toAccountNumber,
        @NotNull BigDecimal transferAmount,
        @NotNull Instant transactionDate,
        @NotNull TransactionStatus status
) {
}
