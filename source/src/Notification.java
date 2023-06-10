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
    
    
        // Getters
    public String getMessage() {
        return message;
    }
    public LocalDateTime getDateSent() {
        return dateSent;
    }
    

    // Data getter
    public NotificationData getData() {
        return new NotificationData(message, dateSent);
    }
}
