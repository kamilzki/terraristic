package com.kamilzki.terraristic.services;

import com.kamilzki.terraristic.commands.CategoryOfAnimalCommand;
import com.kamilzki.terraristic.converters.CategoryOfAnimalToCategoryOfAnimalCommand;
import com.kamilzki.terraristic.domain.CategoryOfAnimal;
import com.kamilzki.terraristic.repositories.CategoryOfAnimalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryOfAnimalServiceImpl implements CategoryOfAnimalService
{
    CategoryOfAnimalRepository categoryOfAnimalRepository;
    CategoryOfAnimalToCategoryOfAnimalCommand categoryOfAnimalToCategoryOfAnimalCommand;

    public CategoryOfAnimalServiceImpl(CategoryOfAnimalRepository categoryOfAnimalRepository, CategoryOfAnimalToCategoryOfAnimalCommand categoryOfAnimalToCategoryOfAnimalCommand)
    {
        this.categoryOfAnimalRepository = categoryOfAnimalRepository;
        this.categoryOfAnimalToCategoryOfAnimalCommand = categoryOfAnimalToCategoryOfAnimalCommand;
    }


    @Override
    public Set<CategoryOfAnimalCommand> listAllCategory()
    {
        return StreamSupport.stream(categoryOfAnimalRepository.findAll()
                .spliterator(), false)
                .map(categoryOfAnimalToCategoryOfAnimalCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public CategoryOfAnimal findById(Long id)
    {
        Optional<CategoryOfAnimal> categoryOptional = categoryOfAnimalRepository.findById(id);

        if (!categoryOptional.isPresent())
        {
            throw new RuntimeException("CategoryOfAnimal Not Found!");
        }

        return categoryOptional.get();
    }

    @Override
    public CategoryOfAnimalCommand findCommandById(Long id)
    {
        return categoryOfAnimalToCategoryOfAnimalCommand.convert(findById(id));
    }
}
