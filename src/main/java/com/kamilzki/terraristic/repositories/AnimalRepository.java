package com.kamilzki.terraristic.repositories;

import com.kamilzki.terraristic.domain.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepository extends CrudRepository<Animal,Long>
{
    Optional<Animal> findById(Long Id);
}
