package com.bobsim.server.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import com.bobsim.server.model.Device;
import com.bobsim.server.model.Event;
import com.bobsim.server.model.Request;
import com.bobsim.server.model.User;
import com.bobsim.server.model.type.Page;
import com.bobsim.server.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class EventController {
    
    final UserService userService;

    @Autowired
    public EventController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/events")
    public ResponseEntity<Event> log(@RequestHeader Integer userId, @RequestHeader Page page, @RequestBody Device device) {
        try {
            User user = userService.findById(userId).get();
            Set<Integer> userItems = user.getItems().stream().map(i -> i.getId()).collect(Collectors.toSet());
            Request request = new Request(device);
            Event event = new Event(user.getId(), userItems, request, page);
            return new ResponseEntity<Event>(event, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
        }
    }

}