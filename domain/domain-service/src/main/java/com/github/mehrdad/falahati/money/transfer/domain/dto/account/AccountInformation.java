package com.github.mehrdad.falahati.money.transfer.domain.dto.account;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountInformation(@NotNull String accountNumber, @NotNull BigDecimal currentBalance) {
}
