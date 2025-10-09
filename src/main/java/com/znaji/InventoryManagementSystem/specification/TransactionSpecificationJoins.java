package com.znaji.InventoryManagementSystem.specification;

import com.znaji.InventoryManagementSystem.entity.Product;
import com.znaji.InventoryManagementSystem.entity.Supplier;
import com.znaji.InventoryManagementSystem.entity.Transaction;
import com.znaji.InventoryManagementSystem.entity.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record TransactionSpecificationJoins(
        Join<Transaction, User> userJoin,
        Join<Transaction, Supplier> supplierJoin,
        Join<Transaction, Product> productJoin
) {
    public static TransactionSpecificationJoins from(Root<Transaction> root) {
        return new TransactionSpecificationJoins(
                root.join("user", JoinType.LEFT),
                root.join("supplier", JoinType.LEFT),
                root.join("product", JoinType.LEFT)
        );
    }
}
