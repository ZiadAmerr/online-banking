import java.time.LocalDateTime;

public class Notification {
    private static int id_counter = 0;
    private final int id;
    private final String message;
    private final LocalDateTime dateSent;
    private final Transaction transaction;
    private final User recipient;

    public Notification(String message, LocalDateTime dateSent, Transaction transaction, User recipient) {
        this.id = id_counter++;
        this.message = message;
        this.dateSent = dateSent;
        this.transaction = transaction;
        this.recipient = recipient;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateTime() {
        return dateSent;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public User getRecipient() {
        return recipient;
    }
}

