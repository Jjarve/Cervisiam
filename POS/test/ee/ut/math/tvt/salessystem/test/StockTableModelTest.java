package ee.ut.math.tvt.salessystem.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;



public class StockTableModelTest {
	private StockTableModel STM;
	private StockItem stockItem;
	  
	@Before
	public void setUp() {
		STM = new StockTableModel();
	}
	
	//TODO
	public void testValidateNameUniqueness() {		
	}
	
	//TODO
	public void testHasEnoughInStock() {
		
	}
	
	@Test
	public void testGetItemByIdWhenItemExists() {
		stockItem = new StockItem(1L,"Lauaviin","Alko 40%",4.95);
		StockItem s2 = STM.getItemById(1);
		assertEquals(s2, stockItem);
		
	}
	
	@Test(expected= NoSuchElementException.class)
	public void testGetItemByIdWhenThrowsException() {
		STM.getItemById(1);
	}

}
