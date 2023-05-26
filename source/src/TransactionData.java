package source.src;

import java.time.LocalDateTime;

public record TransactionData(float amount, int toAccount, int fromAccountNumber, boolean isToItem, String item,
                              LocalDateTime date) {
    public float getAmount(){

        return amount;
    }
    public int getToAccount(){

        return toAccount;
    }
    public int getFromAccountNumber(){

        return fromAccountNumber;
    }
    public boolean getIsToItem(){
        return isToItem;
    }
    public String getItem(){

        return item;
    }
    public LocalDateTime getDate(){
        return date;
    }
}
