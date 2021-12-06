package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertNotNull(SweBank.getName());
		assertEquals(SweBank.getName(), "SweBank");

		assertNotNull(Nordea.getName());
		assertEquals(Nordea.getName(), "Nordea");

		assertNotNull(DanskeBank.getName());
		assertEquals(DanskeBank.getName(), "DanskeBank");
	}

	@Test
	public void testGetCurrency() {
		assertNotNull(SweBank.getCurrency());
		assertEquals(SweBank.getCurrency(), SEK);

		assertNotNull(Nordea.getCurrency());
		assertEquals(Nordea.getCurrency(), SEK);

		assertNotNull(DanskeBank.getCurrency());
		assertEquals(DanskeBank.getCurrency(), DKK);
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		assertFalse(SweBank.accountExist("Vadzim"));
		SweBank.openAccount("Vadzim");
		assertTrue(SweBank.accountExist("Vadzim"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		Integer tmp = SweBank.getBalance("Ulrika");
		SweBank.deposit("Ulrika", new Money(100, SEK));
		assertNotEquals(tmp, SweBank.getBalance("Ulrika"));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		Integer tmp = SweBank.getBalance("Ulrika");
		SweBank.withdraw("Ulrika", new Money(100, SEK));
		assertNotEquals(tmp, SweBank.getBalance("Ulrika"));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		Integer tmp = SweBank.getBalance("Ulrika");
		assertEquals(tmp, SweBank.getBalance("Ulrika"));
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.transfer("Ulrika", Nordea, "Bob", new Money(1000, SEK));
		assertEquals(SweBank.getBalance("Ulrika"), (Integer)(-1000));
		assertEquals(Nordea.getBalance("Bob"), (Integer)(1000));

	}

	//TODO
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {

	}
}
