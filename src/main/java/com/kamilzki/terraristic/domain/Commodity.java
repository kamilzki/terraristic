package com.kamilzki.terraristic.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "commodity")
@Inheritance(strategy = InheritanceType.JOINED)
//@MappedSuperclass
public class Commodity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "name_of_commodity")
    private String name;

    @Lob
    private String description;
    private Double price;

    public Commodity()
    {
    }

    public Commodity(String name, String description, Double price)
    {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

}
