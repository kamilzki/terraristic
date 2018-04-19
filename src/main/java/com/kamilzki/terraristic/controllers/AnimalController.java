package com.kamilzki.terraristic.controllers;

import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.commands.CategoryOfAnimalCommand;
import com.kamilzki.terraristic.domain.CategoryOfAnimal;
import com.kamilzki.terraristic.services.AnimalService;
import com.kamilzki.terraristic.services.CategoryOfAnimalService;
import com.kamilzki.terraristic.services.TypeOfFoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@Controller
public class AnimalController
{
    private final AnimalService animalService;
    private final CategoryOfAnimalService categoryOfAnimalService;
    private final TypeOfFoodService typeOfFoodService;

    public AnimalController(AnimalService animalService, CategoryOfAnimalService categoryOfAnimalService, TypeOfFoodService typeOfFoodService)
    {
        this.animalService = animalService;
        this.categoryOfAnimalService = categoryOfAnimalService;
        this.typeOfFoodService = typeOfFoodService;
    }

    @GetMapping //good habit
    @RequestMapping("/animal/{id}/show")
    public String showById(@PathVariable String id, Model model)
    {
        model.addAttribute("animal", animalService.findById(new Long(id)));

        return "animal/show";
    }

    @GetMapping
    @RequestMapping("animal/new")
    public String newAnimal(Model model)
    {
        AnimalCommand animalCommand = new AnimalCommand();
        model.addAttribute("animal", animalCommand);

        //init categoryOfAnimal
        animalCommand.setCategoryOfAnimal(new CategoryOfAnimalCommand());

        model.addAttribute("allCategories", categoryOfAnimalService.listAllCategory());
        model.addAttribute("allTypeOfFoods", typeOfFoodService.listAllTypeOfFoods());

        return "animal/animalform";
    }

    @GetMapping
    @RequestMapping("animal/{id}/update")
    public String updateAnimal(@PathVariable String id, Model model)
    {
        AnimalCommand animalCommand = animalService.findCommandById(Long.valueOf(id));
        model.addAttribute("animal", animalCommand);

        //init categoryOfAnimal
//        animalCommand.setCategoryOfAnimal(new CategoryOfAnimalCommand());

        model.addAttribute("allCategories", categoryOfAnimalService.listAllCategory());
        model.addAttribute("allTypeOfFoods", typeOfFoodService.listAllTypeOfFoods());

        return "animal/animalform";
    }

    @PostMapping
    @RequestMapping("animal")
//    @RequestMapping(value = "animal", method = RequestMethod.POST) //better up
    public String saveOrUpdate(@ModelAttribute AnimalCommand command)
    {
        AnimalCommand savedCommand = animalService.saveAnimalCommand(command);

        return "redirect:/animal/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("animal/{id}/delete")
    public String deleteAnimal(@PathVariable String id, Model model)
    {
        log.debug("Delete animal id="+id);

        animalService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }
}
