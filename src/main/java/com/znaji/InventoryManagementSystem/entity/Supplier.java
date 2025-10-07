package com.znaji.InventoryManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contactInfo;
    private String address;

    //Relationship:
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getTransactions() {
        return List.copyOf(transactions);
    }

    private void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setSupplier(this);
    }

    private void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
        transaction.setSupplier(null);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return 2002;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Supplier s)) {
            return false;
        }
        return this.id != null && this.id.equals(s.id);
    }
}
