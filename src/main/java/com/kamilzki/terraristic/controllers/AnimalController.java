package com.kamilzki.terraristic.controllers;

import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.services.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class AnimalController
{
    AnimalService animalService;

    public AnimalController(AnimalService animalService)
    {
        this.animalService = animalService;
    }

    @RequestMapping("/animal/{id}/show")
    public String showById(@PathVariable String id, Model model)
    {
        model.addAttribute("animal", animalService.findById(new Long(id)));

        return "animal/show";
    }

    @RequestMapping("animal/new")
    public String newAnimal(Model model)
    {
        model.addAttribute("animal", new AnimalCommand());
        return "animal/animalform";
    }

    @RequestMapping("animal/{id}/update")
    public String updateAnimal(@PathVariable String id, Model model)
    {
        model.addAttribute("animal", animalService.findCommandById(new Long(id)));

        return "animal/recipeform";
    }

    @PostMapping
    @RequestMapping("animal")
//    @RequestMapping(value = "animal", method = RequestMethod.POST) //better up
    public String saveOrUpdate(@ModelAttribute AnimalCommand command)
    {
        AnimalCommand savedCommand = animalService.saveAnimalCommand(command);

        return "redirect:/animal/show/" + savedCommand.getId();
    }
}
