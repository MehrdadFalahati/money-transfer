package com.github.mehrdad.falahati.money.transfer.domain.dto.transaction;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateTransactionResponse(
        @NotNull TransactionResponse information
) {
}
