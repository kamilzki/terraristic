package com.kamilzki.terraristic.commands;

import com.kamilzki.terraristic.domain.Animal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoryOfAnimalCommand
{
    private Long Id;
    private String nameCategory;
    private Set<AnimalCommand> animals = new HashSet<>();

    public Set<AnimalCommand> getAnimals()
    {
        return animals;
    }

    public void setAnimals(Set<AnimalCommand> animals)
    {
        this.animals = animals;
    }
}
