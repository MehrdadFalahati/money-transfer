package com.github.mehrdad.falahati.money.transfer.domain.dto.transaction;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ReportTransactionQuery(
        @NotNull String accountNumber,
        @NotNull String fromDate,
        @NotNull String toDate
) {
}
