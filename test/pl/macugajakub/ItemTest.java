package pl.macugajakub;

import static org.junit.Assert.*;
import org.junit.Test;

public class ItemTest {
    @Test
    public void shouldBeEqual() {
        Item item = new Item("jablko",12.3f);
        assertEquals(item.getName(),"jablko");
        assertEquals(12.3f, item.getPrice(), 0.000001);
    }
}