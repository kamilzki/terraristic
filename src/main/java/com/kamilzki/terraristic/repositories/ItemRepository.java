package com.kamilzki.terraristic.repositories;


import com.kamilzki.terraristic.domain.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item,Long>
{
    Optional<Item> findByName(String name);
}
