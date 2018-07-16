package com.kamilzki.terraristic.converters;

import com.kamilzki.terraristic.commands.UserCommand;
import com.kamilzki.terraristic.domain.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserCommandToUser implements Converter<UserCommand, User>
{

    @Nullable
    @Synchronized
    @Override
    public User convert(UserCommand source)
    {
        final User user = new User();
        user.setId(source.getId());
        user.setUsername(source.getUsername());
        user.setPassword(source.getPassword());
        user.setRoles(source.getRoles());

        return user;
    }
}
