package com.bobsim.server.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(schema = "bobsim", name = "recipe_items")
public class RecipeItem {

    public Long id;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    public Recipe recipe;

    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    public Item item;

    public RecipeItem() {
    }

    public RecipeItem(LocalDateTime createdAt, LocalDateTime updatedAt, Recipe recipe, Item item) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.recipe = recipe;
        this.item = item;
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeItem that = (RecipeItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(recipe, that.recipe) &&
                Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, recipe, item);
    }
}
