package source.src;

import java.time.LocalDateTime;

class Notification {
    public final String message;
    public final LocalDateTime dateSent;

    public Notification(String message) {
        this.message = message;
        this.dateSent = LocalDateTime.now();
    }

    /** Returns an array of the notification details
     * @return The array contains the following elements:
     *     <ol start="0">
     *         <li>message - type: String
     *         <li>dateSent - type: LocalDateTime
     *     </ol>
     */
    public Object[] getData() {
        return new Object[] {message, dateSent};
    }
}

