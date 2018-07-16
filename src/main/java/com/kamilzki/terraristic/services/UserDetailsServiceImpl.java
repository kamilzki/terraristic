package com.kamilzki.terraristic.services;

import com.kamilzki.terraristic.commands.UserCommand;
import com.kamilzki.terraristic.converters.UserCommandToUser;
import com.kamilzki.terraristic.converters.UserToUserCommand;
import com.kamilzki.terraristic.domain.User;
import com.kamilzki.terraristic.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService
{
    private final UserRepository userRepository;
    private final UserCommandToUser userCommandToUser;
    private final UserToUserCommand userToUserCommand;

    public UserDetailsServiceImpl(UserRepository userRepository, UserCommandToUser userCommandToUser, UserToUserCommand userToUserCommand)
    {
        this.userRepository = userRepository;
        this.userCommandToUser = userCommandToUser;
        this.userToUserCommand = userToUserCommand;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (!userOptional.isPresent())
        {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return getUserDetails(userOptional.get());
    }

    private UserDetails getUserDetails(User user)
    {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        org.springframework.security.core.userdetails.User.UserBuilder userBuilder =
                org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
        userBuilder.password(encoder.encode(user.getPassword()));
        userBuilder.roles(user.getRoles());
        return userBuilder.build();
    }

    public UserCommand saveUserCommand(UserCommand userCommand)
    {
        User user = userCommandToUser.convert(userCommand);
        userRepository.save(user);

        log.debug("Saved userId: " + user.getId());
        return userToUserCommand.convert(user);
    }

}
