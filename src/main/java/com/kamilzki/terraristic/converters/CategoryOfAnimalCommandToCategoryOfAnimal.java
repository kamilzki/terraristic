package com.kamilzki.terraristic.converters;

import com.kamilzki.terraristic.commands.CategoryOfAnimalCommand;
import com.kamilzki.terraristic.domain.CategoryOfAnimal;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryOfAnimalCommandToCategoryOfAnimal  implements Converter<CategoryOfAnimalCommand, CategoryOfAnimal>
{

    private final AnimalCommandToAnimal animalConverter;

    public CategoryOfAnimalCommandToCategoryOfAnimal(AnimalCommandToAnimal animalConverter)
    {
        this.animalConverter = animalConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public CategoryOfAnimal convert(CategoryOfAnimalCommand command) {
        if (command == null) {
            return null;
        }

        final CategoryOfAnimal category = new CategoryOfAnimal();
        category.setId(command.getId());
        category.setNameCategory(command.getNameCategory());

        if (command.getAnimals() != null && command.getAnimals().size() > 0){

            command.getAnimals()
                    .forEach(animal ->
                    {
                        System.out.println(animal);
                        System.out.println(animalConverter.convert(animal));
                        category.getAnimals().add(animalConverter.convert(animal));
                    });
        }

        return category;
    }
}