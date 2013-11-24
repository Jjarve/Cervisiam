import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PurchaseInfoTableModelTest {

    private PurchaseInfoTableModel purchaseInfoTabel;
    private SoldItem soldItem;

    @Before
    public void setUp() {
        purchaseInfoTabel = new PurchaseInfoTableModel();
        soldItem = new SoldItem(new StockItem(1L, "Bacardi", "Beer", 2.4, 23), 23);
    }

    @Test
    public void getComlumnValueTest(){

        assertEquals("Bacardi", purchaseInfoTabel.getColumnValue(soldItem, 1));
        assertEquals(2.4, purchaseInfoTabel.getColumnValue(soldItem, 2));
        assertEquals(23, purchaseInfoTabel.getColumnValue(soldItem, 3));
    }
}
