package com.kamilzki.terraristic.services;

import com.kamilzki.terraristic.commands.CategoryOfAnimalCommand;
import com.kamilzki.terraristic.domain.CategoryOfAnimal;

import java.util.Set;

public interface CategoryOfAnimalService
{
    Set<CategoryOfAnimalCommand> listAllCategory();

    CategoryOfAnimal findById(Long id);

    CategoryOfAnimalCommand findCommandById(Long id);
}
