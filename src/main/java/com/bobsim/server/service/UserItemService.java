package com.bobsim.server.service;

import com.bobsim.server.model.UserItem;
import com.bobsim.server.repository.UserItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service @Transactional
public class UserItemService {

    final UserItemRepository repository;

    public UserItemService(UserItemRepository repository) {
        this.repository = repository;
    }
    public List<UserItem> findAll(){ return repository.findAll();}
    public Optional<UserItem> findById(Long id) {return repository.findById(id);}
    public void saveAll(List<UserItem> userItem) {
        repository.saveAll(userItem);
    }
    public void save(UserItem userItem) {
        repository.saveAndFlush(userItem);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
