package com.kamilzki.terraristic.controllers;

import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.services.AnimalService;
import com.kamilzki.terraristic.services.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController
{
    private static final String COMMODITY_ANIMAL_ID = "/commodity/animal/{id}";
    private static final String COMMODITY_ANIMAL = "/commodity/animal";

    private final ImageService imageService;
    private final AnimalService animalService;

    public ImageController(ImageService imageService, AnimalService animalService)
    {
        this.imageService = imageService;
        this.animalService = animalService;
    }

    @GetMapping(COMMODITY_ANIMAL_ID + "/image")
    public String showUploadForm(@PathVariable String id, Model model)
    {
        model.addAttribute("animal", animalService.findCommandById(Long.valueOf(id)));

        return COMMODITY_ANIMAL + "/imageuploadform";
    }

    @PostMapping(COMMODITY_ANIMAL_ID + "/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file)
    {
        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:" + COMMODITY_ANIMAL + id + "/show";
    }

    @GetMapping(COMMODITY_ANIMAL_ID + "/animalimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException
    {
        AnimalCommand animalCommand = animalService.findCommandById(Long.valueOf(id));

        Byte[] bytesImage = animalCommand.getImage();
        if (bytesImage != null)
        {
            byte[] bytes = new byte[bytesImage.length];

            int i = 0;

            for (Byte b : bytesImage)
            {
                bytes[i++] = b;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(bytes);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
