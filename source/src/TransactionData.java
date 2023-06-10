package source.src;

import java.time.LocalDateTime;

public record TransactionData(
        String type,
        int id,
        int fromAccountNumber,
        float amount,
        LocalDateTime date,
        int toAccount,
        boolean isToBuyable,
        String buyableName
) {
    public int getFromAccountNumber() {
        return fromAccountNumber;
    }
    public float getAmount() {
        return amount;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public int getToAccount() {
        return toAccount;
    }
    public boolean getIsToBuyable() {
        return isToBuyable;
    }
    public String getBuyableName() {
        return buyableName;
    }
    public String getType() {
        return type;
    }
    public int getId() {
        return id;
    }
}
