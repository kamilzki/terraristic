package com.kamilzki.terraristic.commands;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserCommand
{
    @NotNull
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private String[] roles;
}
