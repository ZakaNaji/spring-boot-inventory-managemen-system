package com.znaji.InventoryManagementSystem.specification;

import com.znaji.InventoryManagementSystem.entity.Product;
import com.znaji.InventoryManagementSystem.entity.Supplier;
import com.znaji.InventoryManagementSystem.entity.Transaction;
import com.znaji.InventoryManagementSystem.entity.User;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TransactionFilter {

    public static Specification<Transaction> byFilter(String pattern) {
        return (root, query, criteriaBuilder) -> {
            if (pattern == null || pattern.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            var searchValue = "%" + pattern + "%";

            List<Predicate> predicates = new ArrayList<>();
            //add Transaction fields:
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), searchValue));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("note")), searchValue));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("transactionType").as(String.class)), searchValue));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("transactionStatus").as(String.class)), searchValue));

            //Joins:
            TransactionSpecificationJoins transactionSpecificationJoins = TransactionSpecificationJoins.from(root);
            //User fields:
            Join<Transaction, User> userJoin = transactionSpecificationJoins.userJoin();
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("name")), searchValue));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("email")), searchValue));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("phoneNumber")), searchValue));

            //Supplier fields:
            Join<Transaction, Supplier> supplierJoin = transactionSpecificationJoins.supplierJoin();
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(supplierJoin.get("name")), searchValue));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(supplierJoin.get("contactInfo")), searchValue));

            //Product fields:
            Join<Transaction, Product> productJoin = transactionSpecificationJoins.productJoin();
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(productJoin.get("name")), searchValue));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(productJoin.get("sku")), searchValue));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(productJoin.get("description")), searchValue));

            return criteriaBuilder.or(predicates.toArray(Predicate[]::new));
        };
    }

    public static Specification<Transaction> byMonthAndYear(int month, int year) {
        return (root, query, criteriaBuilder) -> {
            Expression<Integer> yearFun = criteriaBuilder.function("year", Integer.class, root.get("createdAt"));
            Expression<Integer> monthFun = criteriaBuilder.function("month", Integer.class, root.get("createdAt"));

            return criteriaBuilder.and(
                    criteriaBuilder.equal(yearFun, year),
                    criteriaBuilder.equal(monthFun, month)
            );
        };
    }
}
