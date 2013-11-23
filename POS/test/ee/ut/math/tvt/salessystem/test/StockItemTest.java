package ee.ut.math.tvt.salessystem.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

private StockItem stockItem;

public class StockItemTest {
	  
	@Before
	public void setUp() {
		stockItem = new Stockitem(1,"Lauaviin","Alko 40%", 4.95, 1);
	}

	@Test
	public void testClone() {
		s2 = stockItem.clone();
		assertEquals(s2, stockItem);
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
		assertEquals(stockItem.getColumn(2), stockItem.getDescription());
		
	}
	
	@Test
	public void testGetColumn3() {
		assertEquals(stockItem.getColumn(3), stockItem.getPrice());		
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetColumnExc() {
		stockItem.getColumn(4)
	}

}
