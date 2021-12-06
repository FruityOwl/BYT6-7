package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals(SEK.getName(), "SEK");
		assertEquals(DKK.getName(), "DKK");
		assertEquals(EUR.getName(), "EUR");
	}
	
	@Test
	public void testGetRate(){
		assertEquals(SEK.getRate(), (Double)0.15);
		assertEquals(DKK.getRate(), (Double)0.20);
		assertEquals(EUR.getRate(), (Double)1.5);
	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.16);
		assertNotEquals(SEK.getRate(), 0.15);
		assertEquals(SEK.getRate(), (Double)0.16);
	}
	
	@Test
	public void testGlobalValue() {
		assertEquals(SEK.universalValue(100), (int)(100/SEK.getRate()));
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals(EUR.valueInThisCurrency(100, SEK), (int)(100/0.15 * 1.5));
	}

}
