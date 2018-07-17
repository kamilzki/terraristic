package com.kamilzki.terraristic.repositories;

import com.kamilzki.terraristic.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
