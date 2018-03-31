package com.kamilzki.terraristic.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "animals")
@Entity
public class CategoryOfAnimal
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_category")
    private String nameCategory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryOfAnimal")
    private Set<Animal> animals = new HashSet<>();

    public CategoryOfAnimal() {}

    public CategoryOfAnimal(String nameCategory, Set<Animal> animals)
    {
        this.nameCategory = nameCategory;
        this.animals = animals;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        System.out.println("setId: "+ id + ", before: " + this.id);
        this.id = id;
        System.out.println("after: " + this.id);
    }

    public String getNameCategory()
    {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory)
    {
        this.nameCategory = nameCategory;
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
