import java.time.LocalDateTime;

public class Notification {
    private int notification_id;
    private String message;
    private LocalDateTime date_time;
    private Transaction transaction;
    private User recipient;

    public int get_notification_id() {
        return 0;
    }

    public String get_message() {
        return "";
    }

    public LocalDateTime get_date_time() {
        return null;
    }

    public Transaction get_transaction() {
        return null;
    }

    public User get_recipient() {
        return null;
    }
}

