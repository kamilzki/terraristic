package com.kamilzki.terraristic.converters;

import com.kamilzki.terraristic.commands.TypeOfFoodCommand;
import com.kamilzki.terraristic.domain.TypeOfFood;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TypeOfFoodCommandToTypeOfFood implements Converter<TypeOfFoodCommand, TypeOfFood>
{
//    private final AnimalCommandToAnimal animalConverter;
//
//    public TypeOfFoodCommandToTypeOfFood(AnimalCommandToAnimal animalConverter)
//    {
//        this.animalConverter = animalConverter;
//    }

    @Synchronized
    @Nullable
    @Override
    public TypeOfFood convert(TypeOfFoodCommand source)
    {
        if (source == null)
        {
            return null;
        }

        final TypeOfFood food = new TypeOfFood();

        food.setId(source.getId());
        food.setNameFood(source.getName());
        food.setAlive(source.isAlive());

//        if (source.getAnimals() != null && source.getAnimals().size() > 0)
//        {
//            source.getAnimals()
//                    .forEach(animal -> food.getAnimals().add(animalConverter.convert(animal)));
//        }

        return food;
    }
}
