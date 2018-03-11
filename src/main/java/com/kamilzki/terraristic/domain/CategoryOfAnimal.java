package com.kamilzki.terraristic.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "animals")
@Entity
public class CategoryOfAnimal
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name_category")
    private String nameCategory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryOfAnimal")
    private Set<Animal> animals;

    public CategoryOfAnimal()
    {
    }

    public CategoryOfAnimal(String nameCategory, Set<Animal> animals)
    {
        this.nameCategory = nameCategory;
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

}
