package source.src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    User user;
    Account acc1;
    static int accNum = 0;
    @BeforeEach
    void SetUp(){
        user = new User("ahmed","ahmed","ah");
        acc1 = new Account(user,"EGP","Savings");
        user.login("ahmed","ah");
        accNum++;
    }

    @Test
    void getAccountByNumber() {
       assertNotNull(Account.getAccountByNumber(0));
    }

    @Test
    void getNonExistingAccountByNumber(){
        assertNull(Account.getAccountByNumber(232));
        assertNull(Account.getAccountByNumber(-2));
    }

    @Test
    void deposit() {
        acc1.deposit(23);
        assertEquals(23,acc1.getBalance());
    }

    @Test
    void depositNegative(){
        assertThrows(IllegalArgumentException.class,()->acc1.deposit(-42));
    }

    @Test
    void withdraw() {
        acc1.deposit(234);
        acc1.withdraw(acc1.getBalance());
        assertEquals(0,acc1.getBalance());
    }
    @Test
    void withdrawExceedBalance(){
        acc1.deposit(100);
        assertFalse(acc1.withdraw(200));
    }

    @Test
    void withdrawNegative(){
        assertThrows(IllegalAccessError.class,()->acc1.withdraw(-43));
    }


    @Test
    void transact(){
        Account acc2 = new Account(user,"EGP","Savings");
        acc1.deposit(300);
        acc1.transact(300,acc2.getNumber());
        assertTrue(acc1.getTransactions().size()>=1);
    }
    @Test
    void transactToNull(){
        acc1.deposit(234);
        assertThrows(IllegalArgumentException.class,()->acc1.transact(200,
                -1));
    }

    @Test
    void testTransact() {
        Shop.addNewItem("cola",12,2);
        acc1.transact("cola");
        assertTrue(acc1.getTransactions().size()>=1);
    }
    @Test
    void transactNonExisting(){
        assertThrows( IllegalArgumentException.class,()->acc1.transact("cola"));
    }

    @Test
    void getBalance() {
        acc1.deposit(33);
        assertEquals(33,acc1.getBalance());
    }

    @Test
    void getTransactions() {
        Account acc2 = new Account(user,"EGP","Savings");
        acc1.deposit(230);
        acc1.transact(200,acc2.getNumber());
        assertEquals(1, acc1.getTransactions().size());
    }

    @Test
    void getUsername() {
        assertEquals("ahmed",acc1.getUsername());
    }

    @Test
    void getNumber() {
        assertEquals(accNum,acc1.getNumber());
    }

    @Test
    void getType() {
        assertEquals("Savings",acc1.getType());
    }

    @Test
    void getCurrency() {
        assertEquals("EGP",acc1.getCurrency());
    }

    @Test
    void getData() {
        AccountData ad = acc1.getData();
        assertTrue(ad.number()==accNum&&ad.currency()=="EGP"&&ad.balance()==0&&ad.type()=="Savings");
    }

    @Test
    void addBill() {
        Bill b = new Bill("cola",32,0);
        acc1.addBill(b);
        assertEquals(1, acc1.getBills().size());
    }
    @Test
    void addBillOtherAccount(){
        Bill b = new Bill("cola",32,-32);
        acc1.addBill(b);
        assertEquals(0, acc1.getBills().size());
    }

    @Test
    void hasBill() {
        Bill b = new Bill("cola",32,0);
        acc1.addBill(b);
        assertTrue(acc1.hasBill("cola"));
        assertFalse(acc1.hasBill("pepsi"));
    }


    @Test
    void hasUnpaidBill() {
        Bill b = new Bill("cola",32,0);
        acc1.addBill(b);
        assertTrue(acc1.hasUnpaidBill("cola"));
        assertFalse(acc1.hasUnpaidBill("pepsi"));
    }

    @Test
    void payBill() {
        Bill b = new Bill("cola",32,0);
        acc1.deposit(32);
        acc1.addBill(b);
        acc1.payBill("cola");
        assertFalse(acc1.hasUnpaidBill("cola"));
    }
    @Test
    void payBillWithNoBalance(){
        Bill b = new Bill("cola",32,0);
        acc1.addBill(b);
        acc1.payBill("cola");
        assertTrue(acc1.hasUnpaidBill("cola"));
    }

    @Test
    void payNonExsBill(){
        assertThrows(IllegalArgumentException.class,()->acc1.payBill("cola"));
    }


    @Test
    void getBills() {
        Bill b = new Bill("cola",32,0);
        acc1.addBill(b);
        assertEquals(1, acc1.getBills().size());
    }

    @Test
    void getBillsData() {
        Bill b = new Bill("cola",32,0);
        acc1.addBill(b);
        BillData bd = acc1.getBillsData().get(0);
        assertEquals("cola",bd.getName());
        assertEquals(32,bd.getPrice());
        assertEquals(0,bd.accountNumber());
        assertFalse(bd.getIsPaid());

    }
}

