package com.bobsim.server.model;


import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(schema = "bobsim", name = "recipes")
public class Recipe {

    public Long id;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    public String name ;

    public String source ;

    public String source_url;
    
    public Recipe() {}

    public Recipe(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String name, String source, String source_url) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.source = source;
        this.source_url = source_url;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) &&
                Objects.equals(createdAt, recipe.createdAt) &&
                Objects.equals(updatedAt, recipe.updatedAt) &&
                Objects.equals(name, recipe.name) &&
                Objects.equals(source, recipe.source) &&
                Objects.equals(source_url, recipe.source_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, name, source, source_url);
    }
}
