package com.kamilzki.terraristic.services;


import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.converters.AnimalCommandToAnimalTest;
import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.repositories.AnimalRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest
{
    @Mock
    AnimalRepository animalRepository;

    ImageService imageService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        imageService = new ImageServiceImpl(animalRepository);
    }

    @Test
    public void saveImageFileTest() throws Exception
    {
        //given
        Long id = 1L;
        MultipartFile file = new MockMultipartFile("imagefile", "testimage.txt",
                "text/plain", "Commodity imagefile".getBytes());

        Animal animal = new Animal();
        animal.setId(id);
        Optional<Animal> optionalAnimal = Optional.of(animal);

        when(animalRepository.findById(anyLong())).thenReturn(optionalAnimal);
        ArgumentCaptor<Animal> argumentCaptor = ArgumentCaptor.forClass(Animal.class);

        //when
        imageService.saveImageFile(id, file);

        //then
        verify(animalRepository, times(1)).save(argumentCaptor.capture());
        Animal savedCommodity = argumentCaptor.getValue();
        assertEquals(file.getBytes().length, savedCommodity.getImage().length);
    }

}
