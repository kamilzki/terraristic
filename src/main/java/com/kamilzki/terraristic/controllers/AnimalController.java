package com.kamilzki.terraristic.controllers;

import com.kamilzki.terraristic.repositories.AnimalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AnimalController
{
    private AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository)
    {
        this.animalRepository = animalRepository;
    }

    @RequestMapping("/animals")
    public String getAuthors(Model model)
    {
        model.addAttribute("animals", animalRepository.findAll());
        return "animals";
    }
}
