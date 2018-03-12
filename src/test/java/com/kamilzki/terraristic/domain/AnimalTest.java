package com.kamilzki.terraristic.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class AnimalTest
{
    Animal animal;

    @Before
    public void setUp() throws Exception
    {
        animal = new Animal();
    }


    @Test
    public void getMinTemperature() throws Exception
    {
        int temperature = 25;
        animal.setMinTemperature(temperature);

        assertEquals(temperature, animal.getMinTemperature());
    }

    @Test
    public void getMaxTemperature() throws Exception
    {
        int temperature = 27;
        animal.setMaxTemperature(temperature);

        assertEquals(temperature, animal.getMaxTemperature());
    }

    @Test
    public void getFoods() throws Exception
    {
        TypeOfFood fruitFood = new TypeOfFood();
        fruitFood.setNameFood("Fruit");

        TypeOfFood insectFood = new TypeOfFood();
        fruitFood.setNameFood("Insect");

        Set<TypeOfFood> foods = new HashSet<>(2);
        foods.add(fruitFood);
        foods.add(insectFood);

        animal.setFoods(foods);

        assertEquals(foods, animal.getFoods());
    }

    @Test
    public void getCategoryOfAnimal() throws Exception
    {
        CategoryOfAnimal category = new CategoryOfAnimal();
        category.setNameCategory("Reptile");

        animal.setCategoryOfAnimal(category);

        assertEquals(category, animal.getCategoryOfAnimal());
    }


}