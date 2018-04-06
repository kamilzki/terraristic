package com.kamilzki.terraristic.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Item extends Commodity
{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String brand;


    public Item(String brand)
    {
        this.brand = brand;
    }

    public Item(String name, String description, Double price, String brand)
    {
        super(name, description, price);
        this.brand = brand;
    }

}