package com.kamilzki.terraristic.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name = "animals")
public class Animal extends Commodity
{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private int minTemperature;
    private int maxTemperature;

    @ManyToMany
    @JoinTable(name = "animal_food",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "type_of_food_id"))
    private Set<TypeOfFood> foods = new HashSet<>();

    @ManyToOne
    private CategoryOfAnimal categoryOfAnimal;

    public Animal(){}

    public Animal(String name, String description, Double price, int minTemperature, int maxTemperature, Set<TypeOfFood> foods, CategoryOfAnimal categoryOfAnimal)
    {
        super(name, description, price);
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.foods = foods;
        this.categoryOfAnimal = categoryOfAnimal;
    }

    public Animal(String name, String description, Double price, int minTemperature, int maxTemperature, CategoryOfAnimal categoryOfAnimal)
    {
        super(name, description, price);
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.categoryOfAnimal = categoryOfAnimal;
    }

    public Animal(int minTemperature, int maxTemperature, Set<TypeOfFood> foods, CategoryOfAnimal categoryOfAnimal)
    {
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.foods = foods;
        this.categoryOfAnimal = categoryOfAnimal;
    }

    public Animal(int minTemperature, int maxTemperature)
    {
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

//    public Long getId()
//    {
//        return id;
//    }
//
//    public void setId(Long id)
//    {
//        this.id = id;
//    }

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

    public Set<TypeOfFood> getFoods()
    {
        return foods;
    }

    public void setFoods(Set<TypeOfFood> foods)
    {
        this.foods = foods;
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
