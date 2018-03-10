package com.kamilzki.terraristic.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CategoryOfAnimal
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @OneToMany
    private Set<Animal> animals;

    public CategoryOfAnimal()
    {
    }

    public CategoryOfAnimal(String name, Set<Animal> animals)
    {
        this.name = name;
        this.animals = animals;
    }

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Animal> getAnimals()
    {
        return animals;
    }

    public void setAnimals(Set<Animal> animals)
    {
        this.animals = animals;
    }
}
