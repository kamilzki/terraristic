package com.kamilzki.terraristic.commands;


import com.kamilzki.terraristic.domain.CategoryOfAnimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
//@NoArgsConstructor
//@EqualsAndHashCode(exclude = "foods")
public class AnimalCommand extends CommodityCommand
{
//    private Long id;
    @Min(1)
    @Max(50)
    private Integer minTemperature;

    @Min(1)
    @Max(50)
    private Integer maxTemperature;
    private Set<TypeOfFoodCommand> foods = new HashSet<>(4);
    private CategoryOfAnimalCommand categoryOfAnimal;

    public AnimalCommand()
    {
        super();
    }

}