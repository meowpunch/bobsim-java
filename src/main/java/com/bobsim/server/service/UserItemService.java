package com.bobsim.server.service;

import com.bobsim.server.model.Item;
import com.bobsim.server.model.User;
import com.bobsim.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserItemService {

    final ItemService itemService;

    final UserRepository userRepository;

    @Autowired
    public UserItemService(ItemService itemService, UserRepository userRepository) {
        this.itemService = itemService;
        this.userRepository = userRepository;
    }

    public List<Item> findAllItemsById(List<Integer> ids) {
        return itemService.findAllByIds(ids);
    }

    public Optional<User> findRecipeById(Integer id) {
        return userRepository.findById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
