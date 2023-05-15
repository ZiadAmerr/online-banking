package source.src;

import java.time.LocalDateTime;

public class Notification {
    private final String message;
    private final LocalDateTime dateSent;

    public Notification(String message) {
        this.message = message;
        this.dateSent = LocalDateTime.now();
    }

    String getMessage() {
        return message;
    }

    LocalDateTime getDateTime() {
        return dateSent;
    }
}

