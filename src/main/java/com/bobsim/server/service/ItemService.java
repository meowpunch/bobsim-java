package com.bobsim.server.service;

import com.bobsim.server.model.Item;
import com.bobsim.server.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemService {

    final ItemRepository repository;

    @Autowired
    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> findAll() {
        return repository.findAll();
    }

    public Optional<Item> findById(Integer id) {
        return repository.findById(id);
    }

    public void saveAll(List<Item> items) {
        repository.saveAll(items);
    }

    public void save(Item item) {
        repository.saveAndFlush(item);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
