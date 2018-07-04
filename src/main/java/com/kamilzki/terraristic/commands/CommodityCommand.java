package com.kamilzki.terraristic.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.JOINED)
public class CommodityCommand
{
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @Size(max = 1000)
    private String description;

    @Min(0)
    private Double price;

    private Byte[] image;
}
