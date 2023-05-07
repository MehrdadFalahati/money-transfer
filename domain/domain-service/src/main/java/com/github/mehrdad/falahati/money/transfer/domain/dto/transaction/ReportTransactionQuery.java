package com.github.mehrdad.falahati.money.transfer.domain.dto.transaction;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

@Builder
public record ReportTransactionQuery(
        @NotNull String accountNumber,
        @NotNull String fromDate,
        @NotNull String toDate
) {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public Instant getFromDate() {
        try {
            return FORMAT.parse(fromDate).toInstant();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Instant getToDate() {
        try {
            return FORMAT.parse(toDate).toInstant();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
