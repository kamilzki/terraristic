package com.kamilzki.terraristic.services;

import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.converters.AnimalCommandToAnimal;
import com.kamilzki.terraristic.converters.AnimalToAnimalCommand;
import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.exceptions.NotFoundException;
import com.kamilzki.terraristic.repositories.AnimalRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class AnimalServiceImplTest
{
    AnimalServiceImpl animalService;

    @Mock
    AnimalRepository animalRepository;

    @Mock
    AnimalCommandToAnimal animalCommandToAnimal;

    @Mock
    AnimalToAnimalCommand animalToAnimalCommand;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        animalService = new AnimalServiceImpl(animalRepository, animalToAnimalCommand, animalCommandToAnimal);
    }

//    @Test
//    public void getAnimals() throws Exception
//    {
//        Animal animal = new Animal();
//        HashSet animalData = new HashSet();
//        animalData.add(animal);
//
//        when(animalService.getAnimals()).thenReturn(animalData);
//
//        Set<Animal> animals = animalService.getAnimals();
//
//        assertEquals(animals.size(), 1);
//        verify(animalRepository, times(1));
//    }

    @Test
    public void getAnimalByIdTest() throws Exception
    {
        Animal animal = new Animal();
        animal.setId(1L);
        Optional<Animal> animalOptional = Optional.of(animal);

        when(animalRepository.findById(anyLong())).thenReturn(animalOptional);

        Animal animalReturned = animalService.findById(1L);

        assertNotNull("Null animal returned", animalReturned);
        verify(animalRepository, times(1)).findById(anyLong());
        verify(animalRepository, never()).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void getAnimalByIdNotFoundTest()
    {
        Optional<Animal> optionalAnimal = Optional.empty();

        when(animalRepository.findById(anyLong())).thenReturn(optionalAnimal);

        Animal animal = animalService.findById(1L);

        //should go boom
    }

    @Test
    public void getAnimalCommandByIdTest() throws Exception
    {
        Animal animal = new Animal();
        animal.setId(1L);
        Optional<Animal> animalOptional = Optional.of(animal);

        when(animalRepository.findById(anyLong())).thenReturn(animalOptional);

        AnimalCommand animalCommand = new AnimalCommand();
        animalCommand.setId(1L);

        when(animalToAnimalCommand.convert(any())).thenReturn(animalCommand);

        AnimalCommand commandById = animalService.findCommandById(1L);

        assertNotNull("Null animal returned", commandById);
        verify(animalRepository, times(1)).findById(anyLong());
        verify(animalRepository, never()).findAll();
    }

    @Test
    public void getAnimalsTest() throws Exception
    {
        Animal animal = new Animal();
        HashSet animalData = new HashSet();
        animalData.add(animal);

        when(animalRepository.findAll()).thenReturn(animalData);

        Set<Animal> animals = animalService.getAnimals();

        assertEquals(animals.size(), 1);
        verify(animalRepository, times(1)).findAll();
        verify(animalRepository, never()).findById(anyLong());
    }

    @Test
    public void testDeleteById() throws Exception
    {

        //given
        Long idToDelete = 2L;

        //when
        animalService.deleteById(idToDelete);

        //no 'when', since method has void return type

        //then
        verify(animalRepository, times(1)).deleteById(anyLong());
    }
}