package com.github.mehrdad.falahati.money.transfer.dataaccess.entity;

import com.github.mehrdad.falahati.money.transfer.domain.valueobject.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction_histories")
@Entity
public class TransactionHistoryEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FROM_ACCOUNT_ID")
    private AccountEntity fromAccount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TO_ACCOUNT_ID")
    private AccountEntity toAccount;

    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Instant createAt;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Version
    @Builder.Default
    private int version = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionHistoryEntity that = (TransactionHistoryEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TransactionHistoryEntity{" +
                "id=" + id +
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", amount=" + amount +
                ", createAt=" + createAt +
                ", status=" + status +
                '}';
    }
}
