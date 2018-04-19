package com.kamilzki.terraristic.services;

import com.kamilzki.terraristic.commands.TypeOfFoodCommand;
import com.kamilzki.terraristic.converters.TypeOfFoodCommandToTypeOfFood;
import com.kamilzki.terraristic.converters.TypeOfFoodToTypeOfFoodCommand;
import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.domain.TypeOfFood;
import com.kamilzki.terraristic.repositories.TypeOfFoodRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TypeOfFoodServiceImpl implements TypeOfFoodService
{
    private final TypeOfFoodRepository foodRepository;
    private final TypeOfFoodToTypeOfFoodCommand typeOfFoodToTypeOfFoodCommand;
    private final TypeOfFoodCommandToTypeOfFood typeOfFoodCommandToTypeOfFood;

    public TypeOfFoodServiceImpl(TypeOfFoodRepository foodRepository, TypeOfFoodToTypeOfFoodCommand typeOfFoodToTypeOfFoodCommand, TypeOfFoodCommandToTypeOfFood typeOfFoodCommandToTypeOfFood)
    {
        this.foodRepository = foodRepository;
        this.typeOfFoodToTypeOfFoodCommand = typeOfFoodToTypeOfFoodCommand;
        this.typeOfFoodCommandToTypeOfFood = typeOfFoodCommandToTypeOfFood;
    }


    @Override
    public Set<TypeOfFoodCommand> listAllTypeOfFoods()
    {
        return StreamSupport.stream(foodRepository.findAll()
                .spliterator(), false)
                .map(typeOfFoodToTypeOfFoodCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public TypeOfFood findById(Long id)
    {
        Optional<TypeOfFood> typeOfFoodOptional = foodRepository.findById(id);

        if (!typeOfFoodOptional.isPresent())
        {
            throw new RuntimeException("TypeOfFood Not Found!");
        }

        return typeOfFoodOptional.get();
    }

    @Override
    public TypeOfFoodCommand findCommandById(Long id)
    {
        return typeOfFoodToTypeOfFoodCommand.convert(findById(id));
    }
}
