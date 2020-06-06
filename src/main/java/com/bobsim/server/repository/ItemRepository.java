package com.bobsim.server.repository;

import com.bobsim.server.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findById(Long id);
}
