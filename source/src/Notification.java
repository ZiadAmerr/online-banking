

import java.time.LocalDateTime;

public class Notification {
    public final String message;
    public final LocalDateTime dateSent;

    public Notification(String message) {
        this.message = message;
        this.dateSent = LocalDateTime.now();
    }
}

