package com.bobsim.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.bobsim.server.model.Recipe;
import com.bobsim.server.model.User;
import com.bobsim.server.repository.RecipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {
    
    final UserService userService;

    final RecipeRepository recipeRepository;

    @Autowired
    public RecommendationService(UserService userService, RecipeRepository recipeRepository) {
        this.userService = userService;
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> recommend(Integer userId) {
        try {
            User user = userService.findById(userId).get();
            List<Integer> items = user.getItems().stream().map(i -> i.getId()).collect(Collectors.toList());
            List<Recipe> recipes = new ArrayList<>(recipeRepository.findByItems(items, items.size()));
            return recipes;
        } catch (NoSuchElementException e) {
            return new ArrayList<>();
        }
    }

}