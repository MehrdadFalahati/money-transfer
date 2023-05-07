package com.github.mehrdad.falahati.money.transfer.application.exception.handler;

import lombok.Builder;

@Builder
public record ErrorDto(String code, String message) {
}
