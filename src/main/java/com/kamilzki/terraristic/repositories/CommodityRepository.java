package com.kamilzki.terraristic.repositories;

import com.kamilzki.terraristic.domain.Commodity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommodityRepository extends CrudRepository<Commodity, Long>
{
    Optional<Commodity> findById(Long idCommodity);
}
