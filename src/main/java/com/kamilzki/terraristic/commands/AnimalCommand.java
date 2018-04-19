package com.kamilzki.terraristic.commands;


import com.kamilzki.terraristic.domain.CategoryOfAnimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
//@NoArgsConstructor
//@EqualsAndHashCode(exclude = "foods")
public class AnimalCommand extends CommodityCommand
{
//    private Long id;
    private Integer minTemperature;
    private Integer maxTemperature;
    private Set<TypeOfFoodCommand> foods = new HashSet<>(4);
    private CategoryOfAnimalCommand categoryOfAnimal;

    public AnimalCommand()
    {
        super();
    }

}