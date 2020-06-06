package com.bobsim.server.api;

import com.bobsim.server.model.Item;
import com.bobsim.server.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping(value = "/get-item")
    @ResponseStatus(value = HttpStatus.OK)
    public String getItem() {
        return "Hello Bobsim!";
    }

    @PostMapping(value = "/add-item")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        try {
            Item newItem = new Item(LocalDateTime.now(), LocalDateTime.now(), item.getName(), item.getSensitivity());
            Item addItem = itemRepository.save(newItem);
            return new ResponseEntity<>(addItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

}
