package com.kamilzki.terraristic.controllers;

import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.services.AnimalService;
import com.kamilzki.terraristic.services.ImageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTest
{

    @Mock
    AnimalService animalService;

    @Mock
    ImageService imageService;

    ImageController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        controller = new ImageController(imageService, animalService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void getImageForm() throws Exception
    {
        //given
        AnimalCommand command = new AnimalCommand();
        command.setId(1L);

        when(animalService.findCommandById(anyLong())).thenReturn(command);

        //when
        mockMvc.perform(get("/commodity/animal/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("animal"));

        verify(animalService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void testGetImageNumberFormatException() throws Exception {

        mockMvc.perform(get("/commodity/animal/a4f/animalimage"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

    @Test
    public void handleImagePost() throws Exception
    {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("imagefile", "testimage.txt",
                "text/plain", "test image".getBytes());

        mockMvc.perform(multipart("/commodity/animal/1/image").file(mockMultipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/commodity/animal/1/show"));

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }

    @Test
    public void renderImageFromDB() throws Exception
    {
        //given
        Long id = 2L;
        AnimalCommand animalCommand = new AnimalCommand();
        animalCommand.setId(id);

        String sImage = "fake string image";
        Byte[] bytes = new Byte[sImage.getBytes().length];

        int i = 0;

        for (byte b : sImage.getBytes())
        {
            bytes[i++] = b;
        }

        animalCommand.setImage(bytes);

        when(animalService.findCommandById(anyLong())).thenReturn(animalCommand);

        //when
        MockHttpServletResponse servletResponse = mockMvc.perform(get("/commodity/animal/1/animalimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = servletResponse.getContentAsByteArray();

        assertEquals(sImage.getBytes().length, responseBytes.length);

    }
}