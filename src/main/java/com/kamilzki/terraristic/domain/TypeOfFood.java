package com.kamilzki.terraristic.domain;

import javax.persistence.*;

@Entity
public class TypeOfFood
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private boolean alive;

    @ManyToOne
    private Animal animal;

    public TypeOfFood()
    {
    }

    public TypeOfFood(String name, boolean alive, Animal animal)
    {
        this.name = name;
        this.alive = alive;
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

    public boolean isAlive()
    {
        return alive;
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
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
