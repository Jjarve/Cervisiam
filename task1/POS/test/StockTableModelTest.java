import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Before testing you need to execute database
 */

public class StockTableModelTest {

    private SalesDomainController salesDomainController;
    private SalesSystemModel salesSystemModel;
    private StockTableModel stockTableModel;
    private static StockItem stockItem1;


    @Before
    public void setUp() {
        stockItem1 = new StockItem(125L, "Hennesy", "The Best one", 856.0, 10);
        salesDomainController = new SalesDomainControllerImpl();
        salesSystemModel = new SalesSystemModel(salesDomainController);
        stockTableModel = salesSystemModel.getWarehouseTableModel();

    }


    @Test
    public void testValidateNameUniqueness() {
        long itemCount = stockTableModel.getRowCount();

        stockTableModel.addItem(stockItem1);
        assertEquals(10, stockTableModel.getItemById(125).getQuantity());

        stockTableModel.addItem(stockItem1);
        assertEquals(20, stockTableModel.getItemById(125).getQuantity());

        assertEquals(itemCount + 1, stockTableModel.getRowCount());

    }

    @Test(expected = NoSuchElementException.class)
    public void testGetItemByIdWhenThrowsException() {
        stockTableModel.addItem(stockItem1);
        stockTableModel.getItemById(666);
    }

    @Test
    public void testGetItemByIdWhenItemExists() {
        stockTableModel.addItem(stockItem1);
        stockTableModel.getItemById(125);
        assertEquals(stockItem1, stockTableModel.getItemById(125));

    }



    @Test
    public void testHasEnoughInStock() {
        stockTableModel.addItem(stockItem1);
        assertTrue(stockTableModel.getItemById(125).getQuantity() >= stockItem1.getQuantity());
    }
}
