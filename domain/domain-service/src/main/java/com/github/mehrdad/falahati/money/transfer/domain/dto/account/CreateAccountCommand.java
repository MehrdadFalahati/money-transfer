package com.github.mehrdad.falahati.money.transfer.domain.dto.account;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public record CreateAccountCommand(@NotNull List<AccountInformation> accounts) {
}
