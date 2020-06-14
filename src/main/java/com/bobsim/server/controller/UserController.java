package com.bobsim.server.controller;

import com.bobsim.server.model.User;
import com.bobsim.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/users")
    public List<User> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        try {
            Optional<User> userOptional = service.findById(id);
            return userOptional
                    .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/users/batch")
    public void addAll(@RequestBody List<User> users) {
        service.saveAll(users);
    }

    @PostMapping(value = "/users")
    public void add(@RequestBody User user) {
        service.save(user);
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody User user) {
        try {
            Optional<User> userOptional = service.findById(id);
            if (userOptional.isPresent()) {
                service.save(user);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/users/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
