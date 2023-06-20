package source.test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import source.src.*;

public class BillTest {

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }
    @Test
    public void testGetAccountNumber() {

        int accountNumber = 123456;
        Bill bill = new Bill("Electricity Bill", 100.0f, accountNumber);
        int actualAccountNumber = bill.getAccountNumber();
        assertEquals(accountNumber, actualAccountNumber);
    }

    @Test
    public void testGetIsPaid() {

        Bill bill = new Bill("Electricity Bill", 100.0f, 123456);
        boolean isPaid = bill.getIsPaid();
        assertFalse(isPaid);
    }

    @Test
    public void testPay() {

        Bill bill = new Bill("Electricity Bill", 100.0f, 123456);
        bill.pay();
        assertTrue(bill.getIsPaid());
    }
}