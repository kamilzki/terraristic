package com.kamilzki.terraristic.repositories;

import com.kamilzki.terraristic.domain.TypeOfFood;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TypeOfFoodRepositoryTest
{

    @Autowired
    TypeOfFoodRepository foodRepository;

    @Before
    public void setUp() throws Exception
    {
    }

    @Test
    public void findByNameFood() throws Exception {

        Optional<TypeOfFood> typeOfFoodOptional = foodRepository.findByNameFood("Fruit");

        assertEquals("Fruit", typeOfFoodOptional.get().getNameFood());
    }

    @Test
    public void findByNameFood2() throws Exception {

        Optional<TypeOfFood> typeOfFoodOptional = foodRepository.findByNameFood("Insect");

        assertEquals("Insect", typeOfFoodOptional.get().getNameFood());
    }

}