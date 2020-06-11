package com.bobsim.server.service;

import com.bobsim.server.model.Item;
import com.bobsim.server.model.User;
import com.bobsim.server.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public List<User> findAll(){ return repository.findAll();}
    public Optional<User> findById(Long id) {return repository.findById(id);}
    public void saveAll(List<User> user) {
        repository.saveAll(user);
    }
    public void save(User user) {
        repository.saveAndFlush(user);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
