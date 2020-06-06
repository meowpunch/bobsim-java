package com.bobsim.server.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(schema = "bobsim", name = "items")
public class Item {

    public Long id;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    public String name;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    public Boolean sensitivity;

    public Item() {}

    public Item(LocalDateTime createdAt, LocalDateTime updatedAt, String name, Boolean sensitivity) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.sensitivity = sensitivity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(Boolean sensitivity) {
        this.sensitivity = sensitivity;
    }

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
