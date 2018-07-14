package com.kamilzki.terraristic.converters;

import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.domain.TypeOfFood;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AnimalToAnimalCommandTest
{
    //todo AnimalCommandToAnimal work, why this no ???

    public static final Long ANIMAL_ID = 1L;
    public static final Integer MAX_TEMP = 30;
    public static final Integer MIN_TEMP = 24;
    public static final String DESCRIPTION = "description";
    public static final String NAME = "name";
    public static final Double PRICE = 321.;

    AnimalToAnimalCommand converter;

    @Before
    public void setUp() throws Exception
    {
        converter = new AnimalToAnimalCommand(new TypeOfFoodToTypeOfFoodCommand());
    }

    @Test
    public void testNullObject() throws Exception
    {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception
    {
        assertNotNull(converter.convert(new Animal()));
    }

    @Test
    public void convert() throws Exception
    {
        Animal animal = new Animal();
        animal.setId(ANIMAL_ID);
        animal.setMinTemperature(MIN_TEMP);
        animal.setMaxTemperature(MAX_TEMP);
        animal.setName(NAME);
        animal.setDescription(DESCRIPTION);
        animal.setPrice(PRICE);

        TypeOfFood food1 = new TypeOfFood();
        food1.setId(1L);

        TypeOfFood food2 = new TypeOfFood();
        food2.setId(2L);

        animal.getFoods().add(food1);
        animal.getFoods().add(food2);

        //when
        AnimalCommand command = converter.convert(animal);

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