package source.test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import source.src.*;

import java.time.LocalDateTime;

public class TransactionTest {

    @Test
    public void getFromAccountNumber() {
        Transaction transaction = new Transaction("Transferral", 100, 0, 1);
        int actualAccountNumber = transaction.getFromAccountNumber();
        assertEquals(0, actualAccountNumber);
    }

    @Test
    public void getToAccountNumber() {
        Transaction transaction = new Transaction("Transferral", 100, 0, 1);
        int actualAccountNumber = transaction.getData().getToAccount();
        assertEquals(1, actualAccountNumber);
    }

    @Test
    public void getAmount() {
        Transaction transaction = new Transaction("Transferral", 100, 0, 1);
        float actualAmount = transaction.getData().getAmount();
        assertEquals(100, actualAmount, 0.0f);
    }

    @Test
    public void testGetTransactionType() {
        Transaction transaction = new Transaction("Transferral", 100, 0, 1);
        String actualTransactionType = transaction.getData().getType();
        assertEquals("Transferral", actualTransactionType);
    }

    @Test
    public void testGetDate() {
        Transaction transaction = new Transaction("Transferral", 100, 0, 1);
        LocalDateTime actualDate = transaction.getData().getDate();
        assertNotNull(actualDate);
    }

    @Test
    public void testGetTransactionData() {
        Transaction transaction = new Transaction("Transferral", 100, 0, 1);
        TransactionData actualTransactionData = transaction.getData();
        assertNotNull(actualTransactionData);
    }

    @Test
    public void testGetTransactionData2() {
        Transaction transaction = new Transaction("Transferral", 100, 0, 1);
        TransactionData actualTransactionData = transaction.getData();
        assertEquals("Transferral", actualTransactionData.getType());
        assertEquals(100, actualTransactionData.getAmount(), 0.0f);
        assertEquals(0, actualTransactionData.getFromAccountNumber());
        assertEquals(1, actualTransactionData.getToAccount());
        assertNotNull(actualTransactionData.getDate());
    }
}