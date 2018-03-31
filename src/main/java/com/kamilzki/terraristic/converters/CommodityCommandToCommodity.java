package com.kamilzki.terraristic.converters;

import com.kamilzki.terraristic.commands.CommodityCommand;
import com.kamilzki.terraristic.domain.Commodity;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommodityCommandToCommodity implements Converter<CommodityCommand, Commodity>
{

    @Synchronized
    @Nullable
    @Override
    public Commodity convert(CommodityCommand source)
    {
        if (source == null)
        {
            return null;
        }

        final Commodity commodity = new Commodity();

        commodity.setId(source.getId());
        commodity.setName(source.getName());
        commodity.setDescription(source.getDescription());
        commodity.setPrice(source.getPrice());

        return commodity;
    }

}


