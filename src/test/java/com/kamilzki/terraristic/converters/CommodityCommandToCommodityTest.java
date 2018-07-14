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
public class CommodityCommandToCommodityTest
{
    public static final Long ID_VALUE = new Long(2L);
    public static final String NAME_VALUE = "name";
    public static final String DESCRIPTION_VALUE = "description";
    public static final Double PRICE_VALUE = 135.31;
    CommodityCommandToCommodity converter;

    @Before
    public void setUp() throws Exception
    {
        converter = new CommodityCommandToCommodity();
    }

    @Test
    public void testNullObject() throws Exception
    {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception
    {
        assertNotNull(converter.convert(new CommodityCommand()));
    }

    @Test
    public void convert() throws Exception
    {
        CommodityCommand commodityCommand = new CommodityCommand();
        commodityCommand.setId(ID_VALUE);
        commodityCommand.setDescription(DESCRIPTION_VALUE);
        commodityCommand.setName(NAME_VALUE);
        commodityCommand.setPrice(PRICE_VALUE);

        Commodity commodity = converter.convert(commodityCommand);

        assertEquals(ID_VALUE, commodity.getId());
        assertEquals(DESCRIPTION_VALUE, commodity.getDescription());
        assertEquals(NAME_VALUE, commodity.getName());
        assertEquals(PRICE_VALUE, commodity.getPrice());
    }
}