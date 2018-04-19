package com.kamilzki.terraristic.commands;

import com.kamilzki.terraristic.domain.Animal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class TypeOfFoodCommand
{
    private Long id;
    private String name;
    private boolean alive;

}

