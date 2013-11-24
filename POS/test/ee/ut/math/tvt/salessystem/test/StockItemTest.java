package ee.ut.math.tvt.salessystem.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;



public class StockItemTest {
	private StockItem stockItem;
	  
	@Before
	public void setUp() {
		stockItem = new StockItem(1L,"Lauaviin","Alko 40%", 4.95, 1);
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
	public void testGetColumn0() {
		assertEquals(stockItem.getColumn(0), stockItem.getId());
		
	}
	
	@Test
	public void testGetColumn1() {
		assertEquals(stockItem.getColumn(1), stockItem.getName());
	}
	
	@Test
	public void testGetColumn2() {
		assertEquals(stockItem.getColumn(2), stockItem.getPrice());
		
	}
	
	@Test
	public void testGetColumn3() {
		assertEquals(stockItem.getColumn(3), stockItem.getQuantity());		
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetColumnExc() {
		stockItem.getColumn(4);
	}

}
