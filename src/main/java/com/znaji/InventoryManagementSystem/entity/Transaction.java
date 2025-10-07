package com.znaji.InventoryManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int totalProducts;

    @Column(scale = 4, precision = 19, nullable = false)
    private BigDecimal totalPrice;

    @Enumerated
    private TransactionType transactionType;
    @Enumerated
    private TransactionStatus transactionStatus;

    private String description;
    private String note;

    @Column(nullable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", totalProducts=" + totalProducts +
                ", totalPrice=" + totalPrice +
                ", transactionType=" + transactionType +
                ", transactionStatus=" + transactionStatus +
                ", description='" + description + '\'' +
                ", note='" + note + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public int hashCode() {
        return 2001;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Transaction t)) {
            return false;
        }
        return this.id != null && this.id.equals(t.id);
    }
}
