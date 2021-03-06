package com.kamilzki.terraristic.repositories;

import com.kamilzki.terraristic.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>
{
    Optional<User> findByUsername(String username);
}
