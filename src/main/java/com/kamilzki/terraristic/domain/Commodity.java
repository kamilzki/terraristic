package com.kamilzki.terraristic.domain;

import javax.persistence.*;

@Entity
@Table(name = "commodity")
@Inheritance(strategy = InheritanceType.JOINED)
//@MappedSuperclass
public class Commodity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }
}
