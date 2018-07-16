package com.kamilzki.terraristic.controllers;

import com.kamilzki.terraristic.repositories.AnimalRepository;
import com.kamilzki.terraristic.services.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class IndexController
{
    private final AnimalService animalService;

    //    @Autowired
    public IndexController(AnimalService animalService)
    {
        this.animalService = animalService;
    }
//
//    private final AnimalRepository animalRepository;
//
//    public IndexController(AnimalRepository animalRepository)
//    {
//        this.animalRepository = animalRepository;
//    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model)
    {
        log.debug("Getting Index page");
//        model.addAttribute("animals", animalRepository.findAll());
        model.addAttribute("animals", animalService.getAnimals());
        return "index";
    }
}
