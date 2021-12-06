package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		//SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("Flat", 30, 1, new Money(1000, SEK),SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("Flat"));
		testAccount.removeTimedPayment("Flat");
		assertFalse(testAccount.timedPaymentExists("Flat"));
	}

	//TODO
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("Flat", 30, 1, new Money(1000, SEK),SweBank, "Alice");
		System.out.println(testAccount.getBalance());
	}

	@Test
	public void testAddWithdraw() {
		Money tmp = testAccount.getBalance();
		testAccount.withdraw(new Money(1000, SEK));
		assertNotSame(tmp, testAccount.getBalance());
	}
	
	@Test
	public void testGetBalance() {
		Money tmp = testAccount.getBalance();
		testAccount.deposit(new Money(1000, SEK));
		assertNotNull(testAccount.getBalance());
		assertNotSame(tmp, testAccount.getBalance());
	}
}
