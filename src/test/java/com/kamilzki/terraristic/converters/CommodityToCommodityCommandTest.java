package com.kamilzki.terraristic.converters;

import com.kamilzki.terraristic.commands.CommodityCommand;
import com.kamilzki.terraristic.domain.Commodity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommodityToCommodityCommandTest
{
    public static final Long ID_VALUE = new Long(2L);
    public static final String NAME_VALUE = "name";
    public static final String DESCRIPTION_VALUE = "description";
    public static final Double PRICE_VALUE = 135.31;
    CommodityToCommodityCommand converter;

    @Before
    public void setUp() throws Exception
    {
        converter = new CommodityToCommodityCommand();
    }

    @Test
    public void testNullObject() throws Exception
    {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception
    {
        assertNotNull(converter.convert(new Commodity()));
    }

    @Test
    public void convert() throws Exception
    {
        Commodity commodity = new Commodity();
        commodity.setId(ID_VALUE);
        commodity.setDescription(DESCRIPTION_VALUE);
        commodity.setName(NAME_VALUE);
        commodity.setPrice(PRICE_VALUE);

        CommodityCommand commodityCommand = converter.convert(commodity);

        assertEquals(ID_VALUE, commodityCommand.getId());
        assertEquals(DESCRIPTION_VALUE, commodityCommand.getDescription());
        assertEquals(NAME_VALUE, commodityCommand.getName());
        assertEquals(PRICE_VALUE, commodityCommand.getPrice());
    }
}