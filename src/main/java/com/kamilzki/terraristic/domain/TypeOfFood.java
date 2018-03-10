package com.kamilzki.terraristic.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class TypeOfFood
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String nameFood;
    private boolean alive;

    @ManyToMany(mappedBy = "foods")
    private Set<Animal> animals;

    public TypeOfFood()
    {
    }

    public TypeOfFood(String nameFood, boolean alive, Set<Animal> animals)
    {
        this.nameFood = nameFood;
        this.alive = alive;
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

    public String getNameFood()
    {
        return nameFood;
    }

    public void setNameFood(String nameFood)
    {
        this.nameFood = nameFood;
    }

    public boolean isAlive()
    {
        return alive;
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
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
