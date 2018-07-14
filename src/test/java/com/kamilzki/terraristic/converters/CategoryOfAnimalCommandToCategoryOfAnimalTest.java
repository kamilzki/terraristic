package com.kamilzki.terraristic.converters;

import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.commands.CategoryOfAnimalCommand;
import com.kamilzki.terraristic.commands.TypeOfFoodCommand;
import com.kamilzki.terraristic.domain.CategoryOfAnimal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryOfAnimalCommandToCategoryOfAnimalTest
{

    public static final Long ID_CATEGORY = 1L;
    public static final String NAME_CATEGORY = "category";
    public static final Long ID_ANIMAL1 = 1L;
    public static final Long ID_ANIMAL2 = 2L;
    public static final Long ID_ANIMAL3 = 34L;
    public static final Integer MAX_TEMP_1 = 30;
    public static final Integer MAX_TEMP_2 = 31;
    public static final Integer MAX_TEMP_3 = 26;
    public static final String NAME_ANIMAL = "animal1";
    public static final Double PRICE_ANIMAL = 320.45;

    CategoryOfAnimalCommandToCategoryOfAnimal converter;

    @Before
    public void setUp() throws Exception
    {
        converter = new CategoryOfAnimalCommandToCategoryOfAnimal(
                new AnimalCommandToAnimal(new TypeOfFoodCommandToTypeOfFood()));
    }

    @Test
    public void convert() throws Exception
    {
        CategoryOfAnimalCommand command = new CategoryOfAnimalCommand();
        command.setId(ID_CATEGORY);
        command.setNameCategory(NAME_CATEGORY);

        AnimalCommand animalCommand = new AnimalCommand();
        animalCommand.setId(ID_ANIMAL1);
//        animalCommand.setMaxTemperature(MAX_TEMP_1);

//        TypeOfFoodCommand food1 = new TypeOfFoodCommand();
//        food1.setId(1L);
//
//        TypeOfFoodCommand food2 = new TypeOfFoodCommand();
//        food2.setId(2L);

//        animalCommand.getFoods().add(food1);
//        animalCommand.getFoods().add(food2);

        AnimalCommand animalCommand2 = new AnimalCommand();
        animalCommand2.setId(ID_ANIMAL2);
//        animalCommand2.setName(NAME_ANIMAL);
//        animalCommand2.setMaxTemperature(MAX_TEMP_2);

        AnimalCommand animalCommand3 = new AnimalCommand();
        animalCommand3.setId(ID_ANIMAL3);
//        animalCommand3.setPrice(PRICE_ANIMAL);
//        animalCommand3.setMaxTemperature(MAX_TEMP_3);

        command.getAnimals().add(animalCommand);
        command.getAnimals().add(animalCommand2);
        command.getAnimals().add(animalCommand3);

        System.out.println("command.getAnimals().size: " + command.getAnimals().size());
        CategoryOfAnimal category = converter.convert(command);

        assertNotNull(category);
        assertEquals(ID_CATEGORY, category.getId());
        assertEquals(NAME_CATEGORY, category.getNameCategory());
        assertEquals(3, category.getAnimals().size());
    }

}