package com.kamilzki.terraristic.converters;

import com.kamilzki.terraristic.commands.CategoryOfAnimalCommand;
import com.kamilzki.terraristic.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryOfAnimalToCategoryOfAnimalCommandTest
{

    public static final Long ID_VALUE = 1L;
    public static final String NAME_CATEGORY_VALUE = "category";
    public static final Long ANIMAL_ID_1 = 1L;
    public static final Long ANIMAL_ID_2 = 2L;
    public static final Integer MAX_TEMP_1 = 30;
    public static final Integer MAX_TEMP_2 = 31;

    CategoryOfAnimalToCategoryOfAnimalCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryOfAnimalToCategoryOfAnimalCommand(
                new AnimalToAnimalCommand(new TypeOfFoodToTypeOfFoodCommand()));
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new CategoryOfAnimal()));
    }

    @Test
    public void convert() throws Exception
    {
        //given
        CategoryOfAnimal categoryOfAnimal = new CategoryOfAnimal();
        categoryOfAnimal.setId(ID_VALUE);
        categoryOfAnimal.setNameCategory(NAME_CATEGORY_VALUE);

        Animal animal1 = new Animal();
        animal1.setId(ANIMAL_ID_1);
//        animal1.setMaxTemperature(MAX_TEMP_1);
//        TypeOfFood food = new TypeOfFood();
//        food.setName("Insect");
//        animal1.getFoods().add(food);

        Animal animal2 = new Animal();
        animal2.setId(ANIMAL_ID_2);
//        animal2.setMaxTemperature(MAX_TEMP_2);

        categoryOfAnimal.getAnimals().add(animal1);
        categoryOfAnimal.getAnimals().add(animal2);

        //when
        CategoryOfAnimalCommand command = converter.convert(categoryOfAnimal);

        //then
        assertNotNull(command);
        assertEquals(ID_VALUE, command.getId());
        assertEquals(NAME_CATEGORY_VALUE, command.getNameCategory());
        assertEquals(2, command.getAnimals().size());
    }

}