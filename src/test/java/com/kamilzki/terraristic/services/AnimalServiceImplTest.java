package com.kamilzki.terraristic.services;

import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.repositories.AnimalRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AnimalServiceImplTest
{
    AnimalServiceImpl animalService;

    @Mock
    AnimalRepository animalRepository;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        animalService = new AnimalServiceImpl(animalRepository);
    }

    @Test
    public void getAnimals() throws Exception
    {
        Animal animal = new Animal();
        HashSet animalData = new HashSet();
        animalData.add(animal);

        when(animalService.getAnimals()).thenReturn(animalData);

        Set<Animal> animals = animalService.getAnimals();

        assertEquals(animals.size(), 1);
        verify(animalRepository, times(1));
    }

}