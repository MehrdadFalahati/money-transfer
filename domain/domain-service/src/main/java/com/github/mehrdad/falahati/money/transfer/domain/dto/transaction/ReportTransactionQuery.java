package com.github.mehrdad.falahati.money.transfer.domain.dto.transaction;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.Instant;

@Builder
public record ReportTransactionQuery(
        @NotNull String accountNumber,
        @NotNull Instant fromDate,
        @NotNull Instant toDate
) {
}
