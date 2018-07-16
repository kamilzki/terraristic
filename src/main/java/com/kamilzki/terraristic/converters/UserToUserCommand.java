package com.kamilzki.terraristic.converters;

import com.kamilzki.terraristic.commands.UserCommand;
import com.kamilzki.terraristic.domain.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserToUserCommand implements Converter<User, UserCommand>
{

    @Nullable
    @Synchronized
    @Override
    public UserCommand convert(User source)
    {
        final UserCommand userCommand = new UserCommand();
        userCommand.setId(source.getId());
        userCommand.setUsername(source.getUsername());
        userCommand.setPassword(source.getPassword());
        userCommand.setRoles(source.getRoles());

        return userCommand;
    }
}
