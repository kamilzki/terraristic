package com.kamilzki.terraristic.services;

import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.repositories.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AnimalServiceImpl implements AnimalService
{
    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository)
    {
        this.animalRepository = animalRepository;
    }

    @Override
    public Set<Animal> getAnimals()
    {
        Set<Animal> animalSet = new HashSet<>();
        animalRepository.findAll().iterator().forEachRemaining(animalSet::add);
        return animalSet;
    }
}
