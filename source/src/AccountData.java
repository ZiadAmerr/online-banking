package source.src;

public record AccountData(int number, float balance, String type, String currency){
    public int getNumber() {
        return number;
    }
    public float getBalance() {
        return balance;
    }
    public String getType() {
        return type;
    }
    public String getCurrency() {
        return currency;
    }
}

