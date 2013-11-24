import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleSaleTest {

    private SoldItem soldItem1;
    private SoldItem soldItem2;

    private PurchaseInfoTableModel purchaseInfoTableModel;


    @Before
    public void setUp(){
        soldItem1 = new SoldItem(new StockItem(100L, "Sour Apple", "Best ever", 4.30, 5), 2);
        soldItem2 = new SoldItem(new StockItem(101L, "VANA-VIINI LAGER", "Good", 5.50, 12), 6);
        purchaseInfoTableModel = new PurchaseInfoTableModel();

    }

    @Test
    public void testAddSoldItem(){
        purchaseInfoTableModel.addItem(soldItem1);
        assertEquals(soldItem1, purchaseInfoTableModel.getTableRows().get(0));
    }

    @Test
    public void testGetSumWithNoItems(){
        assertEquals(0.0, purchaseInfoTableModel.getSumFromCard(), 0.0001);

    }
    @Test
    public void testGetSumWithOneItem(){
        purchaseInfoTableModel.addItem(soldItem1);
        assertEquals(soldItem1.getSum(), purchaseInfoTableModel.getSumFromCard(), 0.0001);

    }
    @Test
    public void testGetSumWithMultipleItems(){
        purchaseInfoTableModel.addItem(soldItem1);
        purchaseInfoTableModel.addItem(soldItem2);
        assertEquals(soldItem1.getSum() + soldItem2.getSum(), purchaseInfoTableModel.getSumFromCard(), 0.0001);

    }
}
