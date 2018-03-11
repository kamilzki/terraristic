package com.kamilzki.terraristic.domain;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "foods")
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

}
