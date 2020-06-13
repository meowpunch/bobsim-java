package com.bobsim.server.controller;

import com.bobsim.server.model.Item;
import com.bobsim.server.model.Recipe;
import com.bobsim.server.service.RecipeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RecipeItemController {

    RecipeItemService recipeItemService;

    @Autowired
    public RecipeItemController(RecipeItemService recipeItemService) {
        this.recipeItemService = recipeItemService;
    }

    @PostMapping(value = "/recipes/{id}/items")
    public ResponseEntity<?> add(@PathVariable Integer id, @RequestBody List<Integer> itemIds) {
        try {
            Set<Item> items = itemIds.stream()
                    .map(itemId -> {
                        Optional<Item> itemOptional = recipeItemService.findItemById(itemId);
                        if (itemOptional.isPresent()) {
                            return itemOptional.get();
                        } else {
                            throw new NoSuchElementException();
                        }
                    })
                    .collect(Collectors.toSet());
            Optional<Recipe> recipeOptional = recipeItemService.findRecipeById(id);
            if (recipeOptional.isPresent()) {
                Recipe recipe =  recipeOptional.get();
                recipe.setItems(items);
                recipeItemService.save(recipe);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
