package com.znaji.InventoryManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "users")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String phoneNumber;

    @Enumerated
    private UserRole role;

    @Column(nullable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Override
    public String toString() {
        return "User{" +
                "createdAt=" + createdAt +
                ", role=" + role +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null ) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (! (o instanceof User u)) {
            return false;
        }
        return u.getEmail().equals(this.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.email);
    }
}
