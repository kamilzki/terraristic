package com.kamilzki.terraristic.converters;

import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.commands.CategoryOfAnimalCommand;
import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.domain.CategoryOfAnimal;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AnimalToAnimalCommand implements Converter<Animal, AnimalCommand>
{
    private final TypeOfFoodToTypeOfFoodCommand foodConverter;

    public AnimalToAnimalCommand(TypeOfFoodToTypeOfFoodCommand foodConverter)
    {
        this.foodConverter = foodConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public AnimalCommand convert(Animal source)
    {
        if (source == null)
        {
            return null;
        }

        final AnimalCommand command = new AnimalCommand();

        command.setId(source.getId());
        command.setMinTemperature(source.getMinTemperature());
        command.setMaxTemperature(source.getMaxTemperature());
        command.setName(source.getName());
        command.setPrice(source.getPrice());
        command.setDescription(source.getDescription());
        command.setImage(source.getImage());
//        command.setCategoryOfAnimal(source.getCategoryOfAnimal());
        CategoryOfAnimalCommand coacommand = new CategoryOfAnimalCommand();
        CategoryOfAnimal coa = source.getCategoryOfAnimal();
        coacommand.setId(coa.getId());
        coacommand.setNameCategory(coa.getNameCategory());
        command.setCategoryOfAnimal(coacommand);

        if (source.getFoods() != null && source.getFoods().size() > 0)
        {
            source.getFoods()
                    .forEach(food-> command.getFoods().add(foodConverter.convert(food)));
        }

        return command;
    }
}
