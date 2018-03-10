package com.kamilzki.terraristic.controllers;

import com.kamilzki.terraristic.repositories.AnimalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
//    private final AnimalService animalService;
//
////    @Autowired
//    public IndexController(AnimalService animalService)
//    {
//        this.animalService = animalService;
//    }

    private final AnimalRepository animalRepository;

    public IndexController(AnimalRepository animalRepository)
    {
        this.animalRepository = animalRepository;
    }

    @RequestMapping({"", "/", "/index"})
    private String getIndexPage(Model model)
    {
        model.addAttribute("animals", animalRepository.findAll());
        return "index";
    }
}
