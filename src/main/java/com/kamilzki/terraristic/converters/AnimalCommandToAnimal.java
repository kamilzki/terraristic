package com.kamilzki.terraristic.converters;


import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.commands.CategoryOfAnimalCommand;
import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.domain.CategoryOfAnimal;
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

        //todo, find way to save category
        CategoryOfAnimalCommand coacommand = animalCommand.getCategoryOfAnimal();
        if (coacommand != null)
        {
            CategoryOfAnimal coa = new CategoryOfAnimal();
            coa.setId(coacommand.getId());
            coa.setNameCategory(coacommand.getNameCategory());
            animal.setCategoryOfAnimal(coa);
//            coa.setAnimals(coacommand.getAnimals());
//            animal.setCategoryOfAnimal(animalCommand.getCategoryOfAnimal().getId());
        }
        if (animalCommand.getFoods() != null && animalCommand.getFoods().size() > 0)
        {
            animalCommand.getFoods()
                    .forEach(food -> animal.getFoods().add(foodConverter.convert(food)));
        }

        return animal;
    }
}
