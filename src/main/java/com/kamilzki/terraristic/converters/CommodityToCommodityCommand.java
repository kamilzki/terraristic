package com.kamilzki.terraristic.converters;

import com.kamilzki.terraristic.commands.CommodityCommand;
import com.kamilzki.terraristic.domain.Commodity;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommodityToCommodityCommand implements Converter<Commodity, CommodityCommand>
{
    @Synchronized
    @Nullable
    @Override
    public CommodityCommand convert(Commodity source)
    {
        if (source == null)
        {
            return null;
        }

        final CommodityCommand commodityCommand = new CommodityCommand();

        commodityCommand.setId(source.getId());
        commodityCommand.setName(source.getName());
        commodityCommand.setDescription(source.getDescription());
        commodityCommand.setPrice(source.getPrice());

        return commodityCommand;
    }
}
