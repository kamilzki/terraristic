package com.kamilzki.terraristic.controllers;

import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.services.AnimalService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class AnimalControllerTest
{
    @Mock
    AnimalService animalService;

    AnimalController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new AnimalController(animalService);
    }

    @Test
    public void testGetRecipe() throws Exception {

        Animal animal = new Animal();
        animal.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(animalService.findById(anyLong())).thenReturn(animal);

        mockMvc.perform(get("/animal/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("animal/show"))
                .andExpect(model().attributeExists("animal"));
    }

}