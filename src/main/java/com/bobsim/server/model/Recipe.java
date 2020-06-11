package com.bobsim.server.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(schema = "bobsim", name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Long id;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    public String name;

    public String source;

    public String source_url;

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
