package com.bobsim.server.service;

import com.bobsim.server.model.RecipeItem;
import com.bobsim.server.repository.RecipeItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeItemService {

    final RecipeItemRepository repository;

    @Autowired
    public RecipeItemService(RecipeItemRepository repository) {
        this.repository = repository;
    }

    public List<RecipeItem> findAll() {
        return repository.findAll();
    }

    public Optional<RecipeItem> findById(Long id) {
        return repository.findById(id);
    }

    public void saveAll(List<RecipeItem> RecipeItems) {
        repository.saveAll(RecipeItems);
    }

    public void save(RecipeItem RecipeItem) {
        repository.saveAndFlush(RecipeItem);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
