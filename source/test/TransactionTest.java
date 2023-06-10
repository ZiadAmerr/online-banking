package source.test;

import org.junit.jupiter.api.Test;
import source.src.AccountData;
import source.src.Shop;
import source.src.Transaction;
import source.src.User;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void getFromAccountNumber() {
        Shop.addNewItem("cola",23,100);
        Transaction transaction = new Transaction(1,"cola");
        assertEquals(1,transaction.getFromAccountNumber());
    }


}