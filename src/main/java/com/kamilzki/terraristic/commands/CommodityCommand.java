package com.kamilzki.terraristic.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Getter
@Setter
@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.JOINED)
public class CommodityCommand
{
    private Long Id;
    private String name;
    private String description;
    private Double price;
}
