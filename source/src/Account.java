package source.src;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private static final ArrayList<Account> accounts = new ArrayList<>();
    private static int counter = 0;
    private final int number;
    private float balance;
    private final String currency;
    private final String type;
    private final ArrayList<Transaction> transactions = new ArrayList<>();
    private final ArrayList<Bill> bills = new ArrayList<>();
    private final User user;


    // Constructor
    Account(User user, String currency, String type) {
        number = counter++;
        balance = 0;
        this.user = user;
        if (!currency.equals("EGP") && !currency.equals("USD"))
            throw new IllegalArgumentException(String.format("Currency must be EGP or USD not %s", currency));

        if (!type.equals("Checking") && !type.equals("Savings"))
            throw new IllegalArgumentException(String.format("Type must be Checking or Savings not %s", type));

        this.currency = currency;
        this.type = type;
        accounts.add(this);
    }


    // Static functions
    public static Account getAccountByNumber(int number) {
        for(Account account : accounts)
            if (account.getNumber() == number)
                return account;
        return null;
    }


    // Money functions
    public void deposit(float amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount must be positive");

        balance += amount;
    }
    public boolean withdraw(float amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount must be positive");

        float newBalance = balance - amount;
        if (newBalance < 0) {
            return false;
        }

        balance = newBalance;
        return true;
    }


    // Transaction functions
    public void transact(float amount, int otherAccountNumber) {
        Account other = getAccountByNumber(otherAccountNumber);
        if (other == null)
            throw new IllegalArgumentException(String.format("Account with number %d does not exist", otherAccountNumber));

        transactions.add(new Transaction("Money Transfer", amount, this.getNumber(), otherAccountNumber));
    }
    public void transact(String name) {
        if (!Shop.itemExisted(name))
            throw new IllegalArgumentException(String.format("Item with name %s does not exist", name));

        transactions.add(new Transaction("Payment", this.getNumber(), name));
    }


    // getters
    public float getBalance() {
        return balance;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public String getUsername() {
        return user.getUsername();
    }
    public int getNumber() {
        return number;
    }
    public String getType() {
        return type;
    }
    public String getCurrency() {
        return currency;
    }
    public AccountData getData() {
        return new AccountData(
                number,
                balance,
                currency,
                type
        );
    }
    void addBill(Bill bill) {
        Account account = Account.getAccountByNumber(bill.getAccountNumber());
        if (account == null)
            throw new IllegalArgumentException("Account does not exist");

        if (bill.getPrice() < 0)
            throw new IllegalArgumentException("Price cannot be negative");
        bills.add(bill);
    }
    public boolean hasBill(String name) {
        for (Bill bill : bills)
            if (bill.getName().equals(name))
                return true;
        return false;
    }
    public boolean hasUnpaidBill(String name) {
        if (!hasBill(name))
            return false;

        for (Bill bill : bills)
            if (bill.getName().equals(name))
                return !bill.getIsPaid();
        return false;
    }
    public boolean payBill(String name) {
        if (!hasBill(name))
            throw new IllegalArgumentException(String.format("Bill with name %s does not exist", name));

        if (!hasUnpaidBill(name))
            throw new IllegalArgumentException(String.format("Bill with name %s is already paid", name));


        for (Bill bill : bills)
            if (bill.getName().equals(name)) {
                bill.pay();
                return true;
            }
        return false;
    }
    public List<Bill> getBills() {
        return bills;
    }


    // Data getter
    public List<BillData> getBillsData() {
        return bills
                .stream()
                .map(Bill::getData)
                .toList();
    }
}