

import java.time.LocalDateTime;

public class Notification {
    private static int id_counter = 0;
    private final int id;
    private final String message;
    private final LocalDateTime dateSent;

    public Notification(String message) {
        this.id = id_counter++;
        this.message = message;
        this.dateSent = LocalDateTime.now();
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
}

