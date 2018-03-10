package com.kamilzki.terraristic.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Animal //extends Commodity
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int minTemperature;
    private int maxTemperature;

    @OneToMany
    private Set<TypeOfFood> typeOfFoods = new HashSet<>();

    @ManyToOne
    private CategoryOfAnimal categoryOfAnimal;

    public Animal(){}

    public Animal(int minTemperature, int maxTemperature, Set<TypeOfFood> typeOfFoods, CategoryOfAnimal categoryOfAnimal)
    {
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.typeOfFoods = typeOfFoods;
        this.categoryOfAnimal = categoryOfAnimal;
    }

    public Animal(int minTemperature, int maxTemperature)
    {
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public int getMinTemperature()
    {
        return minTemperature;
    }

    public void setMinTemperature(int minTemperature)
    {
        this.minTemperature = minTemperature;
    }

    public int getMaxTemperature()
    {
        return maxTemperature;
    }

    public void setMaxTemperature(int maxTemperature)
    {
        this.maxTemperature = maxTemperature;
    }

    public Set<TypeOfFood> getTypeOfFoods()
    {
        return typeOfFoods;
    }

    public void setTypeOfFoods(Set<TypeOfFood> typeOfFoods)
    {
        this.typeOfFoods = typeOfFoods;
    }

    public CategoryOfAnimal getCategoryOfAnimal()
    {
        return categoryOfAnimal;
    }

    public void setCategoryOfAnimal(CategoryOfAnimal categoryOfAnimal)
    {
        this.categoryOfAnimal = categoryOfAnimal;
    }
}
