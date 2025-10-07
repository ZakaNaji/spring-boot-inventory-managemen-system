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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //Relationship:
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public void addProduct(Product p) {
        products.add(p);
        p.setCategory(this);
    }

    public void removeProduct(Product p) {
        products.remove(p);
        p.setCategory(null);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
        if (!(o instanceof Category c)) {
            return false;
        }
        return this.id != null && this.id.equals(c.id);
    }
}
