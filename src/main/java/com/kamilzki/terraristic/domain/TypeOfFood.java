package com.kamilzki.terraristic.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "animals")
@Entity
public class TypeOfFood
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameFood;
    private boolean alive;

    @ManyToMany(mappedBy = "foods")
    private Set<Animal> animals;

    public TypeOfFood() {}

    public TypeOfFood(String nameFood, boolean alive, Set<Animal> animals)
    {
        this.nameFood = nameFood;
        this.alive = alive;
        this.animals = animals;
    }

    public boolean isAlive()
    {
        return alive;
    }


}
