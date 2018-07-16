package com.kamilzki.terraristic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String[] roles;

    public User(String username, String password, String[] roles)
    {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
