package com.bobsim.server.controller;

import java.util.List;
import java.util.NoSuchElementException;

import com.bobsim.server.model.Recipe;
import com.bobsim.server.service.RecommendationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RecommendationController {
    
    final RecommendationService service;

    @Autowired
    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @GetMapping(value = "/recommend/{id}")
    public ResponseEntity<List<Recipe>> recommend(@PathVariable Integer id) {
        try {
            List<Recipe> recipes = service.recommend(id);
            return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<List<Recipe>>(HttpStatus.NOT_FOUND);
        }
    }

}