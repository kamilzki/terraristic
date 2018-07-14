package com.kamilzki.terraristic.converters;

import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.commands.TypeOfFoodCommand;
import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.domain.TypeOfFood;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AnimalCommandToAnimalTest
{
    public static final Long ANIMAL_ID = 1L;
    public static final Integer MAX_TEMP = 30;
    public static final Integer MIN_TEMP = 24;
    public static final String DESCRIPTION = "description";
    public static final String NAME = "name";
    public static final Double PRICE = 321.;

    AnimalCommandToAnimal converter;

    @Before
    public void setUp() throws Exception
    {
        converter = new AnimalCommandToAnimal(new TypeOfFoodCommandToTypeOfFood());
    }

    @Test
    public void testNullObject() throws Exception
    {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception
    {
        assertNotNull(converter.convert(new AnimalCommand()));
    }


    @Test
    public void convert() throws Exception
    {
        //given
        AnimalCommand command = new AnimalCommand();
        command.setId(ANIMAL_ID);
        command.setMaxTemperature(MAX_TEMP);
        command.setMinTemperature(MIN_TEMP);
        command.setName(NAME);
        command.setDescription(DESCRIPTION);
        command.setPrice(PRICE);

        TypeOfFoodCommand food1 = new TypeOfFoodCommand();
        food1.setId(1L);

        TypeOfFoodCommand food2 = new TypeOfFoodCommand();
        food2.setId(2L);

        command.getFoods().add(food1);
        command.getFoods().add(food2);

        //when
        Animal animal = converter.convert(command);

        //then
        assertEquals(ANIMAL_ID, animal.getId());
        assertEquals(MAX_TEMP, animal.getMaxTemperature());
        assertEquals(MIN_TEMP, animal.getMinTemperature());
        assertEquals(2, animal.getFoods().size());
        assertEquals(NAME, animal.getName());
        assertEquals(DESCRIPTION, animal.getDescription());
        assertEquals(PRICE, animal.getPrice());
    }
}