package com.kamilzki.terraristic.controllers;

import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.repositories.AnimalRepository;
import com.kamilzki.terraristic.services.AnimalService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest
{
    @Mock
    AnimalService animalService;

    @Mock
    Model model;

    IndexController controller;


    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        controller = new IndexController(animalService);
    }

    @Test
    public void testMockMVC() throws Exception
    {
        MockMvc mockMvc = new MockMvcBuilders().standaloneSetup(controller).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() throws Exception
    {
        //given
        Set<Animal> animals = new HashSet<>(2);
        animals.add(new Animal());

        Animal animal = new Animal();
        animal.setMinTemperature(19);
        animals.add(animal);

        when(animalService.getAnimals()).thenReturn(animals);

        ArgumentCaptor<Set<Animal>> animalArgumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = controller.getIndexPage(model);

        //then
        assertEquals("index", viewName);
        Mockito.verify(animalService, times(1)).getAnimals();
        Mockito.verify(model, times(1)).addAttribute(eq("animals"), animalArgumentCaptor.capture());

        Set<Animal> animalSetInController = animalArgumentCaptor.getValue();
        assertEquals(2, animalSetInController.size());
    }
}