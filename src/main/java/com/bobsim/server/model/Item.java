package com.bobsim.server.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(schema = "bobsim", name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Long id;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    public String name;

    @Column(nullable = false, columnDefinition = "BIT", length = 1)
    public Boolean sensitivity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id) &&
                createdAt.equals(item.createdAt) &&
                updatedAt.equals(item.updatedAt) &&
                Objects.equals(name, item.name) &&
                Objects.equals(sensitivity, item.sensitivity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, name, sensitivity);
    }
}
