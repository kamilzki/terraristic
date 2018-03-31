package com.kamilzki.terraristic.converters;


import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.domain.Animal;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AnimalCommandToAnimal implements Converter<AnimalCommand, Animal>
{

    private final TypeOfFoodCommandToTypeOfFood foodConverter;

    public AnimalCommandToAnimal(TypeOfFoodCommandToTypeOfFood foodConverter)
    {
        this.foodConverter = foodConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Animal convert(AnimalCommand animalCommand)
    {
        if (animalCommand == null)
        {
            return null;
        }

        final Animal animal = new Animal();

        animal.setId(animalCommand.getId()); //commodity inheritance
        animal.setName(animalCommand.getName()); //commodity inheritance
        animal.setPrice(animalCommand.getPrice()); //commodity inheritance
        animal.setDescription(animalCommand.getDescription()); //commodity inheritance
        animal.setMinTemperature(animalCommand.getMinTemperature());
        animal.setMaxTemperature(animalCommand.getMaxTemperature());

//        animal.setCategoryOfAnimal(animalCommand.getCategoryOfAnimal());

        if (animalCommand.getFoods() != null && animalCommand.getFoods().size() > 0)
        {
            animalCommand.getFoods()
                    .forEach(food-> animal.getFoods().add(foodConverter.convert(food)));
        }

        return animal;
    }
}
