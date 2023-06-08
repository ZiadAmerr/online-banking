package source.src;

import java.time.LocalDateTime;

public class Notification {
    private final String message;
    private final LocalDateTime dateSent;


    // Constructor
    public Notification(String message) {
        this.message = message;
        this.dateSent = LocalDateTime.now();
    }


    // Data getter
    public NotificationData getData() {
        return new NotificationData(message, dateSent);
    }
}