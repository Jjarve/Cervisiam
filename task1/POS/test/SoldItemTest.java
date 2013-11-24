import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SoldItemTest {

    private StockItem stockItem;
    private SoldItem soldItem;

    @Before
    public void setUp() {
        stockItem = new StockItem(17L, "Sour Apple", "Best ever", 4.30, 5);
    }

    @Test
    public void testGetSum(){
        soldItem = new SoldItem(stockItem, 5);
        assertEquals(21.5, soldItem.getSum(), 0.0001);
    }

    @Test
    public void testGetSumWithZeroQuantity(){
        soldItem = new SoldItem(stockItem, 0);
        assertEquals( 0.0, soldItem.getSum(), 0.0001);

    }
}