package com.kamilzki.terraristic.repositories;


import com.kamilzki.terraristic.domain.TypeOfFood;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TypeOfFoodRepository extends CrudRepository<TypeOfFood,Long>
{
    Optional<TypeOfFood> findByNameFood(String nameFood);
}
