import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.domain.data.SingleSale;
import ee.ut.math.tvt.salessystem.ui.model.HistoryTableModel;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Before testing you need to execute database
 */

public class HistoryTableModelTest {

    private SalesDomainController domainController = new SalesDomainControllerImpl();
    private HistoryTableModel model = new HistoryTableModel();
    private List<SingleSale> singleSales;


    @Before
    public void setUp() {
        singleSales = domainController.loadSalesHistory();
        model.populateWithData(singleSales);
    }

    @Test
    public void testGetColumeValue() {
        SingleSale singleSale =  model.getItemById(1);
        assertEquals(model.getColumnValue(singleSale, 0), singleSale.getId());
        assertEquals(model.getColumnValue(singleSale, 1), singleSale.getDate());
        assertEquals(model.getColumnValue(singleSale, 2), singleSale.getSum());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testColumnException() {
        SingleSale itemById = model.getItemById(1);
        model.getColumnValue(itemById, 5);

    }


}
