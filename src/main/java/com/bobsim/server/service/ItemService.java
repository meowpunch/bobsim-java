package com.bobsim.server.service;

import com.bobsim.server.model.Item;
import com.bobsim.server.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public void addItem(Item item) {
        itemRepository.saveAndFlush(item);
    }
}
