package com.bobsim.server.api;

import com.bobsim.server.model.Item;
import com.bobsim.server.service.ItemService;
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
public class ItemController {

    final ItemService service;

    @Autowired
    public ItemController(ItemService service) { this.service = service; }

    @GetMapping(value = "/items")
    public List<Item> findAll() { return service.findAll(); }

    @GetMapping(value = "/items/{id}")
    public ResponseEntity<Item> get(@PathVariable Long id) {
        try {
            Optional<Item> item = service.findById(id);
            return item
                    .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/items/batch")
    public void addAll(@RequestBody List<Item> items) {
        List<Item> updated = items.stream().peek(i -> {
            i.setCreatedAt(LocalDateTime.now());
            i.setUpdatedAt(LocalDateTime.now());
        }).collect(Collectors.toList());
        service.saveAll(updated);
    }

    @PostMapping(value = "/items")
    public void add(@RequestBody Item item) {
        item.setCreatedAt(LocalDateTime.now());
        item.setUpdatedAt(LocalDateTime.now());
        service.save(item);
    }

    @PutMapping(value = "/items/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Item item) {
        try {
            Optional<Item> itemOptional = service.findById(id);
            if (itemOptional.isPresent()) {
                item.setId(itemOptional.get().getId());
                item.setCreatedAt(itemOptional.get().getCreatedAt());
                item.setUpdatedAt(LocalDateTime.now());
                service.save(item);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/items/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
