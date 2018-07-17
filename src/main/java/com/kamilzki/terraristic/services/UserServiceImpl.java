package com.kamilzki.terraristic.services;

import com.kamilzki.terraristic.domain.Role;
import com.kamilzki.terraristic.domain.User;
import com.kamilzki.terraristic.repositories.RoleRepository;
import com.kamilzki.terraristic.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder)
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public void saveUser(User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));

        Set setRole = new HashSet<Role>();
        roleRepository.findAll().forEach(setRole::add);

        user.setRoles(setRole);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }
}
