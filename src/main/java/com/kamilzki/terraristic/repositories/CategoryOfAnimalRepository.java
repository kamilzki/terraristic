package com.kamilzki.terraristic.repositories;

import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.domain.CategoryOfAnimal;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryOfAnimalRepository extends CrudRepository<CategoryOfAnimal,Long>
{
    Optional<CategoryOfAnimal> findByNameCategory(String nameCategory);

    Optional<CategoryOfAnimal> findById(Long id);
}
