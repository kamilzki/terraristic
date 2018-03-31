package com.kamilzki.terraristic.services;

import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.converters.AnimalCommandToAnimal;
import com.kamilzki.terraristic.converters.AnimalToAnimalCommand;
import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.repositories.AnimalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class AnimalServiceImpl implements AnimalService{

    private final AnimalRepository animalRepository;
    private final AnimalToAnimalCommand animalToAnimalCommand;
    private final AnimalCommandToAnimal animalCommandToAnimal;

    public AnimalServiceImpl(AnimalRepository animalRepository, AnimalToAnimalCommand animalToAnimalCommand, AnimalCommandToAnimal animalCommandToAnimal)
    {
        this.animalRepository = animalRepository;
        this.animalToAnimalCommand = animalToAnimalCommand;
        this.animalCommandToAnimal = animalCommandToAnimal;
    }

    @Override
    public Set<Animal> getAnimals()
    {
        log.debug("Animal service - getAnimals");
        Set<Animal> animalSet = new HashSet<>();
        animalRepository.findAll().iterator().forEachRemaining(animalSet::add);
        return animalSet;
    }

    @Override
    public Animal findById(Long id)
    {
        Optional<Animal> animalOptional = animalRepository.findById(id);

        if (!animalOptional.isPresent())
        {
            throw new RuntimeException("Animal Not Found!");
        }

        return animalOptional.get();
    }

    @Override
    public AnimalCommand findCommandById(Long id)
    {
        return animalToAnimalCommand.convert(findById(id));
    }

    @Override
    @Transactional
    public AnimalCommand saveAnimalCommand(AnimalCommand command)
    {
        Animal detachedAnimal = animalCommandToAnimal.convert(command);

        Animal savedAnimal = animalRepository.save(detachedAnimal);
        log.debug("Saved AnimalId:" + savedAnimal.getId());
        return animalToAnimalCommand.convert(savedAnimal);
    }

}
