package source.src;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class BillTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    void testGetAccountNumber() {

        int accountNumber = 123456;
        Bill bill = new Bill("Electricity Bill", 100.0f, accountNumber);
        int actualAccountNumber = bill.getAccountNumber();
        assertEquals(accountNumber, actualAccountNumber);
    }

    @Test
    void testGetIsPaid() {

        Bill bill = new Bill("Electricity Bill", 100.0f, 123456);
        boolean isPaid = bill.getIsPaid();
        assertFalse(isPaid);
    }

    @Test
    void testPay() {

        Bill bill = new Bill("Electricity Bill", 100.0f, 123456);
        bill.pay();
        assertTrue(bill.getIsPaid());
    }
}