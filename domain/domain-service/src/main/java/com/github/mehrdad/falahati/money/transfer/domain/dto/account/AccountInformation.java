package com.github.mehrdad.falahati.money.transfer.domain.dto.account;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public record AccountInformation(@NotNull String accountNumber, @NotNull BigDecimal currentBalance) {
}
