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
    private Animal animal;

    public CategoryOfAnimal()
    {
    }

    public CategoryOfAnimal(String name, Animal animal)
    {
        this.name = name;
        this.animal = animal;
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

    public Animal getAnimal()
    {
        return animal;
    }

    public void setAnimal(Animal animal)
    {
        this.animal = animal;
    }
}
