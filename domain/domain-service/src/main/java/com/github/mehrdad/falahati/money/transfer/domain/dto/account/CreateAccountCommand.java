package com.github.mehrdad.falahati.money.transfer.domain.dto.account;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record CreateAccountCommand(@NotNull List<AccountInformation> accounts) {
}
