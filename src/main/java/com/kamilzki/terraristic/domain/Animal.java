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
    private Integer minTemperature;
    private Integer maxTemperature;

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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        if (!super.equals(o)) return false;

        Animal animal = (Animal) o;

        if (getMinTemperature() != null ? !getMinTemperature().equals(animal.getMinTemperature()) : animal.getMinTemperature() != null)
            return false;
        if (getMaxTemperature() != null ? !getMaxTemperature().equals(animal.getMaxTemperature()) : animal.getMaxTemperature() != null)
            return false;
        if (getFoods() != null ? !getFoods().equals(animal.getFoods()) : animal.getFoods() != null) return false;
        return getCategoryOfAnimal() != null ? getCategoryOfAnimal().equals(animal.getCategoryOfAnimal()) : animal.getCategoryOfAnimal() == null;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (getMinTemperature() != null ? getMinTemperature().hashCode() : 0);
        result = 31 * result + (getMaxTemperature() != null ? getMaxTemperature().hashCode() : 0);
        result = 31 * result + (getFoods() != null ? getFoods().hashCode() : 0);
        result = 31 * result + (getCategoryOfAnimal() != null ? getCategoryOfAnimal().hashCode() : 0);
        return result;
    }
}
