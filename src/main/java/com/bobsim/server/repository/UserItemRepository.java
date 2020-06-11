package com.bobsim.server.repository;

import com.bobsim.server.model.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserItemRepository extends JpaRepository<UserItem, Long>{

}