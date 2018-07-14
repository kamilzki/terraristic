package com.kamilzki.terraristic.controllers;

import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.commands.CategoryOfAnimalCommand;
import com.kamilzki.terraristic.exceptions.NotFoundException;
import com.kamilzki.terraristic.services.AnimalService;
import com.kamilzki.terraristic.services.CategoryOfAnimalService;
import com.kamilzki.terraristic.services.TypeOfFoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@Controller
public class AnimalController
{
    private static final String COMMODITY_ANIMAL_ANIMALFORM = "commodity/animal/animalform";

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
    @RequestMapping("/commodity/animal/{id}/show")
    public String showById(@PathVariable String id, Model model)
    {
        model.addAttribute("animal", animalService.findById(new Long(id)));

        return "commodity/animal/show";
    }

    @GetMapping
    @RequestMapping("commodity/animal/new")
    public String newAnimal(Model model)
    {
        log.debug("get - new Animal");
        AnimalCommand animalCommand = new AnimalCommand();
        model.addAttribute("animal", animalCommand);

        //init categoryOfAnimal
        animalCommand.setCategoryOfAnimal(new CategoryOfAnimalCommand());

        model.addAttribute("allCategories", categoryOfAnimalService.listAllCategory());
        model.addAttribute("allTypeOfFoods", typeOfFoodService.listAllTypeOfFoods());

        return COMMODITY_ANIMAL_ANIMALFORM;
    }

    @GetMapping
    @RequestMapping("commodity/animal/{id}/update")
    public String updateAnimal(@PathVariable String id, Model model)
    {
        log.debug("get - update Animal");
        AnimalCommand animalCommand = animalService.findCommandById(Long.valueOf(id));
        model.addAttribute("animal", animalCommand);

        //init categoryOfAnimal
//        animalCommand.setCategoryOfAnimal(new CategoryOfAnimalCommand());

        model.addAttribute("allCategories", categoryOfAnimalService.listAllCategory());
        model.addAttribute("allTypeOfFoods", typeOfFoodService.listAllTypeOfFoods());

        return COMMODITY_ANIMAL_ANIMALFORM;
    }

    @PostMapping("commodity/animal")
    public String saveOrUpdate(@Valid @ModelAttribute("animal") AnimalCommand command, BindingResult bindingResult)
    {
        log.debug("post - saveOrUpdate");
        if (bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().forEach(objectError ->
            {
                log.debug(objectError.toString());
            });
            Map<String, Object> model = bindingResult.getModel();
            model.put("allCategories", categoryOfAnimalService.listAllCategory());
            return COMMODITY_ANIMAL_ANIMALFORM;
        }

        AnimalCommand savedCommand = animalService.saveAnimalCommand(command);


        return "redirect:/commodity/animal/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("commodity/animal/{id}/delete")
    public String deleteAnimal(@PathVariable String id, Model model)
    {
        log.debug("Delete animal id=" + id);

        animalService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }

}
