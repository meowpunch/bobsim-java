package com.bobsim.server.service;

import com.bobsim.server.model.Item;
import com.bobsim.server.model.Recipe;
import com.bobsim.server.repository.ItemRepository;
import com.bobsim.server.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RecipeItemService {

    final ItemRepository itemRepository;

    final RecipeRepository recipeRepository;

    @Autowired
    public RecipeItemService(ItemRepository itemRepository, RecipeRepository recipeRepository) {
        this.itemRepository = itemRepository;
        this.recipeRepository = recipeRepository;
    }

    public Optional<Item> findItemById(Integer id) {
        return itemRepository.findById(id);
    }

    public Optional<Recipe> findRecipeById(Integer id) {
        return recipeRepository.findById(id);
    }

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }
}
