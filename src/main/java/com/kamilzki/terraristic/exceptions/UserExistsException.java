package com.kamilzki.terraristic.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "UserExists")
public final class UserExistsException extends RuntimeException
{
    public UserExistsException()
    {
    }

    public UserExistsException(String message)
    {
        super(message);
    }

    public UserExistsException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public UserExistsException(Throwable cause)
    {
        super(cause);
    }
}

