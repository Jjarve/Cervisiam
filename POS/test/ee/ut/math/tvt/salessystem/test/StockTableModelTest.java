package ee.ut.math.tvt.salessystem.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

private StockTableModel() STM;
private StockItem stockItem;

public class StockTableModelTest {
	  
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
		stockItem = new StockItem(1,"Lauaviin","Alko 40%",4.95);
		s2 = getItemById(1);
		assertEquals(s2, stockItem);
		
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testGetItemByIdWhenThrowsException() {
		getItemById(1);
	}

}
