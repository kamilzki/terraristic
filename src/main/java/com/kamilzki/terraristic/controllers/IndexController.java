package com.kamilzki.terraristic.controllers;

import com.kamilzki.terraristic.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
    private final AnimalService animalService;

    @Autowired
    public IndexController(AnimalService animalService)
    {
        this.animalService = animalService;
    }

    @RequestMapping({"", "/", "/index"})
    private String getIndexPage(Model model)
    {
        model.addAttribute("animals", animalService.getAnimals());

        return "index";
    }
}
