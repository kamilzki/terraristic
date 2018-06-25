package com.kamilzki.terraristic.services;

import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.repositories.AnimalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService
{

    private final AnimalRepository animalRepository;

    public ImageServiceImpl(AnimalRepository animalRepository)
    {
        this.animalRepository = animalRepository;
    }

    @Override
    public void saveImageFile(Long animalId, MultipartFile file)
    {
        try
        {
            Animal animal = animalRepository.findById(animalId).get();

            Byte[] byteObject = new Byte[file.getBytes().length];
            
            int i=0;

            for (byte b: file.getBytes())
            {
                byteObject[i++] = b;
            }

            animal.setImage(byteObject);

            animalRepository.save(animal);
        }
        catch (IOException e)
        {
            //todo write better handler
            log.error("Error occured!", e);
            e.printStackTrace();

        }
    }


}
