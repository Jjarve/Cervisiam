import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class StockItemTest {

    private StockItem stockItem;


    @Before
    public void setUp() {
        stockItem = new StockItem(17L, "Sour Apple", "Best ever", 4.30, 5);
    }

    @Test
    public void testClone() {
        StockItem s2 = stockItem.clone();
        assertEquals(s2.getId(), stockItem.getId());
        assertEquals(s2.getName(), stockItem.getName());
        assertEquals(s2.getDescription(), stockItem.getDescription());
        assertEquals(s2.getQuantity(), stockItem.getQuantity());
    }

    @Test
    public void testGetColumn() {
        assertEquals(17L, stockItem.getColumn(0));
        assertEquals("Sour Apple", stockItem.getColumn(1));
        assertEquals(4.30, stockItem.getColumn(2));
        assertEquals(5, stockItem.getColumn(3));

    }
}
