package com.kamilzki.terraristic.converters;

import com.kamilzki.terraristic.commands.CategoryOfAnimalCommand;
import com.kamilzki.terraristic.domain.CategoryOfAnimal;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryOfAnimalToCategoryOfAnimalCommand implements Converter<CategoryOfAnimal, CategoryOfAnimalCommand>
{

    private final AnimalToAnimalCommand animalConverter;

    public CategoryOfAnimalToCategoryOfAnimalCommand(AnimalToAnimalCommand animalConverter)
    {
        this.animalConverter = animalConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public CategoryOfAnimalCommand convert(CategoryOfAnimal source)
    {
        if (source == null)
        {
            System.out.println("null!!");
            return null;
        }

        final CategoryOfAnimalCommand categoryCommand = new CategoryOfAnimalCommand();

        categoryCommand.setId(source.getId());
        categoryCommand.setNameCategory(source.getNameCategory());

        if (source.getAnimals() != null && source.getAnimals().size() > 0)
        {
            source.getAnimals()
                    .forEach(animal -> categoryCommand.getAnimals().add(animalConverter.convert(animal)));
        }

        return categoryCommand;
    }

}
