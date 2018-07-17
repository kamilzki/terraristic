package com.kamilzki.terraristic.bootstrap;

import com.kamilzki.terraristic.domain.Role;
import com.kamilzki.terraristic.domain.User;
import com.kamilzki.terraristic.repositories.RoleRepository;
import com.kamilzki.terraristic.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserAndRoleBootstrap implements ApplicationListener<ContextRefreshedEvent>
{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    public UserAndRoleBootstrap(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder)
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        roleRepository.saveAll(getRoles());
        userRepository.saveAll(getUsers());
    }

    private List<Role> getRoles()
    {
        return Arrays.asList(
                new Role("USER"),
                new Role("ADMIN")
        );
    }

    private List<User> getUsers()
    {
        List<User> users = new ArrayList<>();
        User admin = new User("admin", encoder.encode("admin"));

        Set setRole = new HashSet<Role>();
        roleRepository.findAll().forEach(setRole::add);
        admin.setRoles(setRole);
        users.add(admin);

        User user = new User("user", encoder.encode("user"));
        setRole = new HashSet<Role>();
        setRole.add(roleRepository.findByName("USER"));
        user.setRoles(setRole);
        users.add(user);

        return users;
    }
}
