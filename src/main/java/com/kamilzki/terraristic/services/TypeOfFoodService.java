package com.kamilzki.terraristic.services;

import com.kamilzki.terraristic.commands.CategoryOfAnimalCommand;
import com.kamilzki.terraristic.commands.TypeOfFoodCommand;
import com.kamilzki.terraristic.domain.CategoryOfAnimal;
import com.kamilzki.terraristic.domain.TypeOfFood;

import java.util.Set;

public interface TypeOfFoodService
{
    Set<TypeOfFoodCommand> listAllTypeOfFoods();

    TypeOfFood findById(Long id);

    TypeOfFoodCommand findCommandById(Long id);
}
