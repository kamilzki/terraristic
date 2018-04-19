package com.kamilzki.terraristic.converters;

import com.kamilzki.terraristic.commands.TypeOfFoodCommand;
import com.kamilzki.terraristic.domain.TypeOfFood;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TypeOfFoodToTypeOfFoodCommand implements Converter<TypeOfFood, TypeOfFoodCommand>
{

//    private final AnimalToAnimalCommand animalConverter;
//
//    public TypeOfFoodToTypeOfFoodCommand(AnimalToAnimalCommand animalConverter)
//    {
//        this.animalConverter = animalConverter;
//    }

    @Synchronized
    @Nullable
    @Override
    public TypeOfFoodCommand convert(TypeOfFood source)
    {
        if (source == null)
        {
            return null;
        }

        final TypeOfFoodCommand command = new TypeOfFoodCommand();

        command.setId(source.getId());
        command.setName(source.getNameFood());
        command.setAlive(source.isAlive());

//        if (source.getAnimals() != null && source.getAnimals().size() > 0)
//        {
//            source.getAnimals()
//                    .forEach((Animal animal) -> command.getAnimals().add(animalConverter.convert(animal)));
//        }

        return command;
    }
}