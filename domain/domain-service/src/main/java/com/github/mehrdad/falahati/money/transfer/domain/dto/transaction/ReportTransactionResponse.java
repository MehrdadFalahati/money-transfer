package com.github.mehrdad.falahati.money.transfer.domain.dto.transaction;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public record ReportTransactionResponse(@NotNull List<TransactionResponse> transactions) {
}
