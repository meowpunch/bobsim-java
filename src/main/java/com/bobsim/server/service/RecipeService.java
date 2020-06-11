package com.bobsim.server.service;

import com.bobsim.server.model.Recipe;
import com.bobsim.server.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeService {

    final RecipeRepository repository;

    @Autowired
    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }

    public List<Recipe> findAll() {
        return repository.findAll();
    }

    public Optional<Recipe> findById(Long id) {
        return repository.findById(id);
    }

    public void saveAll(List<Recipe> items) {
        repository.saveAll(items);
    }

    public void save(Recipe recipe) {
        repository.saveAndFlush(recipe);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

