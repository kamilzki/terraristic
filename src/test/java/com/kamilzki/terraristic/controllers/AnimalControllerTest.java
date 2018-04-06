package com.kamilzki.terraristic.controllers;

import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.services.AnimalService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class AnimalControllerTest
{
    @Mock
    AnimalService animalService;

    AnimalController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new AnimalController(animalService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAnimal() throws Exception {

        Animal animal = new Animal();
        animal.setId(1L);

        when(animalService.findById(anyLong())).thenReturn(animal);

        mockMvc.perform(get("/animal/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("animal/show"))
                .andExpect(model().attributeExists("animal"));
    }

    @Test
    public void testGetNewAnimalForm() throws Exception {
        AnimalCommand command = new AnimalCommand();

        mockMvc.perform(get("/animal/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("animal/animalform"))
                .andExpect(model().attributeExists("animal"));
    }

    @Test
    public void testPostNewAnimalForm() throws Exception {
        AnimalCommand command = new AnimalCommand();
        command.setId(2L);

        when(animalService.saveAnimalCommand(any())).thenReturn(command);

        mockMvc.perform(post("/animal")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/animal/2/show"));
    }

    @Test
    public void testGetUpdateView() throws Exception
    {
        AnimalCommand command = new AnimalCommand();
        command.setId(33L);

        when(animalService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/animal/33/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("animal/animalform"))
                .andExpect(model().attributeExists("animal"));
    }

    @Test
    public void testDeleteAction() throws Exception
    {
        mockMvc.perform(get("/animal/3/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(animalService, times(1)).deleteById(anyLong());
    }

}