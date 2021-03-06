package com.kamilzki.terraristic.controllers;

import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.commands.CategoryOfAnimalCommand;
import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.exceptions.NotFoundException;
import com.kamilzki.terraristic.services.AnimalService;
import com.kamilzki.terraristic.services.CategoryOfAnimalService;
import com.kamilzki.terraristic.services.TypeOfFoodService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AnimalControllerTest
{
    @Mock
    AnimalService animalService;

    @Mock
    CategoryOfAnimalService categoryOfAnimalService;

    @Mock
    TypeOfFoodService typeOfFoodService;

    AnimalController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        controller = new AnimalController(animalService, categoryOfAnimalService, typeOfFoodService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetAnimal() throws Exception
    {

        Animal animal = new Animal();
        animal.setId(1L);

        when(animalService.findById(anyLong())).thenReturn(animal);

        mockMvc.perform(get("/commodity/animal/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("commodity/animal/show"))
                .andExpect(model().attributeExists("animal"));
    }

    @Test
    public void testGetAnimalNotFound() throws Exception
    {
        when(animalService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/commodity/animal/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    public void testGetAnimalNumberFormatException() throws Exception
    {

        mockMvc.perform(get("/commodity/animal/1w2/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

    @Test
    public void testGetNewAnimalForm() throws Exception
    {
        AnimalCommand command = new AnimalCommand();

        HashSet<CategoryOfAnimalCommand> categories = new HashSet<>();

        CategoryOfAnimalCommand category1 = new CategoryOfAnimalCommand();
        category1.setId(1L);
        categories.add(category1);

        CategoryOfAnimalCommand category2 = new CategoryOfAnimalCommand();
        category1.setId(2L);
        categories.add(category2);


        when(categoryOfAnimalService.listAllCategory()).thenReturn(categories);

        mockMvc.perform(get("/commodity/animal/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("commodity/animal/animalform"))
                .andExpect(model().attributeExists("animal"))
                .andExpect(model().attributeExists("allCategories"));

        verify(animalService, times(0)).findById(anyLong());
        verify(categoryOfAnimalService, times(1)).listAllCategory();
    }

    @Test
    public void testPostNewAnimalForm() throws Exception
    {
        AnimalCommand command = new AnimalCommand();
        command.setId(2L);
        CategoryOfAnimalCommand categoryOfAnimalCommand = new CategoryOfAnimalCommand();
        categoryOfAnimalCommand.setId(1L);
        categoryOfAnimalCommand.setNameCategory("category");

        command.setCategoryOfAnimal(categoryOfAnimalCommand);

        when(animalService.saveAnimalCommand(any())).thenReturn(command);

        mockMvc.perform(post("/commodity/animal")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "some name")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/commodity/animal/2/show"));
    }

    @Test
    public void testPostNewAnimalFormValidationFail() throws Exception
    {
        AnimalCommand command = new AnimalCommand();
        command.setId(2L);

        when(animalService.saveAnimalCommand(any())).thenReturn(command);

        mockMvc.perform(post("/commodity/animal")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("animal"))
                .andExpect(view().name("commodity/animal/animalform"));
    }

    @Test
    public void testGetUpdateView() throws Exception
    {
        AnimalCommand command = new AnimalCommand();
        command.setId(33L);

        when(animalService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/commodity/animal/33/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("commodity/animal/animalform"))
                .andExpect(model().attributeExists("animal"));
    }

    @Test
    public void testDeleteAction() throws Exception
    {
        mockMvc.perform(get("/commodity/animal/3/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(animalService, times(1)).deleteById(anyLong());
    }

}