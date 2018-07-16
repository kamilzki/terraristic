package com.kamilzki.terraristic.commands;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class UserCommand
{
    private Long id;

    @NotBlank
    @Size(min = 4, max = 40)
    private String username;

    @NotBlank
    @Size(min = 4, max = 60)
    private String password;

    private String[] roles;
}
