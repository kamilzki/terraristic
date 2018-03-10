package com.kamilzki.terraristic.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item extends Commodity
{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long Id;
    private String brand;

    public Item()
    {
    }

    public Item(String brand)
    {
        this.brand = brand;
    }

    public Item(String name, String description, Double price, String brand)
    {
        super(name, description, price);
        this.brand = brand;
    }

//    public Long getId()
//    {
//        return Id;
//    }
//
//    public void setId(Long id)
//    {
//        Id = id;
//    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }
}