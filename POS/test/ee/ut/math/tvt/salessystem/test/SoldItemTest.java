package ee.ut.math.tvt.salessystem.test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;


private StockItem stockItem; // le' problém
private SoldItem soldItem;


public class SoldItemTest {
	  
	@Before
	public void setUp() {
	   stockItem = new StockItem(1,"Lauaviin","Alko 40%",4.95);
	   
	}
	
	@Test
	public void testGetSum() {
		soldItem = new SoldItem(stockItem,1);
	    assertEquals(soldItem.getSum(), stockItem.getPrice(), 0.0001);		
	}
	
	@Test
	public void testGetSumWithZeroQuantity() {
		soldItem = new SoldItem(stockItem,0);
	    assertEquals(soldItem.getSum(), 0.0, 0.0001);	
	}

}
