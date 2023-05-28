package source.src;

import java.time.LocalDateTime;

public record NotificationData(
        String message,
        LocalDateTime dateSent
) {
    public String getMessage() {
        return message;
    }
    public LocalDateTime getDateSent() {
        return dateSent;
    }
}