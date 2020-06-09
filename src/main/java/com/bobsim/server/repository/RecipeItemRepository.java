package com.bobsim.server.repository;

import com.bobsim.server.model.RecipeItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeItemRepository extends JpaRepository<RecipeItem, Long>  {

}
