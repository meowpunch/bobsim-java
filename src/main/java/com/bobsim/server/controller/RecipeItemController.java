package com.bobsim.server.controller;

import com.bobsim.server.model.RecipeItem;
import com.bobsim.server.service.RecipeItemService;
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
public class RecipeItemController {

    final RecipeItemService service;

    @Autowired
    public RecipeItemController(RecipeItemService service) { this.service = service; }

    @GetMapping(value = "/recipe_items")
    public List<RecipeItem> findAll() { return service.findAll(); }

    @GetMapping(value = "/recipe_items/{id}")
    public ResponseEntity<RecipeItem> get(@PathVariable Long id) {
        try {
            Optional<RecipeItem> RecipeItem = service.findById(id);
            return RecipeItem
                    .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/recipe_items/batch")
    public void addAll(@RequestBody List<RecipeItem> RecipeItems) {
        List<RecipeItem> updated = RecipeItems.stream().peek(i -> {
            i.setCreatedAt(LocalDateTime.now());
            i.setUpdatedAt(LocalDateTime.now());
        }).collect(Collectors.toList());
        service.saveAll(updated);
    }

    @PostMapping(value = "/recipe_items")
    public void add(@RequestBody RecipeItem RecipeItem) {
        RecipeItem.setCreatedAt(LocalDateTime.now());
        RecipeItem.setUpdatedAt(LocalDateTime.now());
        service.save(RecipeItem);
    }

    @PutMapping(value = "/recipe_items/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RecipeItem RecipeItem) {
        try {
            Optional<RecipeItem> RecipeItemOptional = service.findById(id);
            if (RecipeItemOptional.isPresent()) {
                RecipeItem.setId(RecipeItemOptional.get().getId());
                RecipeItem.setCreatedAt(RecipeItemOptional.get().getCreatedAt());
                RecipeItem.setUpdatedAt(LocalDateTime.now());
                service.save(RecipeItem);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/recipe_items/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
