package com.github.mehrdad.falahati.money.transfer.dataaccess.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@Entity
public class AccountEntity {
    @Id
    private UUID id;
    private String accountNumber;
    private BigDecimal currentBalance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
