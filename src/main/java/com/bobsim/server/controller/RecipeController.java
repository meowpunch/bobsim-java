package com.bobsim.server.controller;


import com.bobsim.server.model.Recipe;
import com.bobsim.server.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RecipeController {
    
    final RecipeService service;
    
    @Autowired
    public RecipeController(RecipeService service){ this.service = service;}
    
    @GetMapping(value = "/recipes") 
    public List<Recipe> findAll() { return service.findAll();}
    
    @GetMapping(value = "/recipes/{id}")
    public ResponseEntity<Recipe> get(@PathVariable Long id) {
        try {
            Optional<Recipe> recipe = service.findById(id);
            return recipe
                    .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/recipes/batch")
    public void addAll(@RequestBody List<Recipe> recipes) {
        List<Recipe> updated = recipes.stream().peek(i -> {
            i.setCreatedAt(LocalDateTime.now());
            i.setUpdatedAt(LocalDateTime.now());
        }).collect(Collectors.toList());
        service.saveAll(updated);
    }

    @PostMapping(value = "/recipes")
    public void add(@RequestBody Recipe recipe) {
        recipe.setCreatedAt(LocalDateTime.now());
        recipe.setUpdatedAt(LocalDateTime.now());
        service.save(recipe);
    }

    @PutMapping(value = "/recipes/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Recipe recipe) {
        try {
            Optional<Recipe> recipeOptional = service.findById(id);
            if (recipeOptional.isPresent()) {
                recipe.setId(recipeOptional.get().getId());
                recipe.setCreatedAt(recipeOptional.get().getCreatedAt());
                recipe.setUpdatedAt(LocalDateTime.now());
                service.save(recipe);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/recipes/{id}")
    public void delete(@PathVariable Long id) { service.delete(id);
    }
    
}
