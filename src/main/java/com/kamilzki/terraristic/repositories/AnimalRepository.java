package com.kamilzki.terraristic.repositories;

import com.kamilzki.terraristic.domain.Animal;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AnimalRepository extends CrudRepository<Animal,Long>
{
//    Optional<Animal> findByName(String name );
}
