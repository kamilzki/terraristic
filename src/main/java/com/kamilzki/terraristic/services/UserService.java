package com.kamilzki.terraristic.services;

import com.kamilzki.terraristic.domain.User;

public interface UserService
{
    void saveUser(User user);

    User findByUsername(String username);
}
