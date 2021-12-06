package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals(EUR10.getAmount(), (Integer)1000);
	}

	@Test
	public void testGetCurrency() {
		assertEquals(EUR10.getCurrency(), EUR);
	}

	@Test
	public void testToString() {
		assertEquals(EUR10.toString(), "10 EUR");
	}

	@Test
	public void testGlobalValue() {
		assertEquals(EUR10.universalValue(), (Integer)666);
	}

	@Test
	public void testEqualsMoney() {
		assertTrue(EUR10.equals(new Money(100, SEK)));
	}

	@Test
	public void testAdd() {
		assertEquals(EUR20.add(SEK100).toString(), "1020 EUR");
	}

	@Test
	public void testSub() {
		assertEquals(EUR20.sub(SEK100).toString(), "-980 EUR");
	}

	@Test
	public void testIsZero() {
		assertTrue(SEK0.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals(EUR10.negate().toString(), "-10 EUR");
	}

	@Test
	public void testCompareTo(){
		assertEquals(EUR10.compareTo(SEK100), -1);
		assertEquals(EUR10.compareTo(EUR10), 0);
	}
}
