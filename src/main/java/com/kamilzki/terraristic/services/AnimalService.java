package com.kamilzki.terraristic.services;

import com.kamilzki.terraristic.domain.Animal;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface AnimalService
{
    Set<Animal> getAnimals();
}
