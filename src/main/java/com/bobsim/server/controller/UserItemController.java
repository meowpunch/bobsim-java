package com.bobsim.server.controller;

import com.bobsim.server.model.Item;
import com.bobsim.server.model.User;
import com.bobsim.server.service.UserItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class UserItemController {

    final UserItemService service;

    @Autowired
    public UserItemController(UserItemService service) {
        this.service = service;
    }

    @PostMapping(value = "/users/{id}/items")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody List<Integer> itemIds) {
        try {
            Optional<User> userOptional = service.findUserById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                Set<Item> items = user.getItems();
                Set<Item> newItems = new HashSet<>(service.findAllItemsById(itemIds));
                items.addAll(newItems);
                user.setItems(items);
                service.save(user);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
