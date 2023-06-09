package source.test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import source.src.*;

public class NotificationTest {

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetData() {
        String message = "Hello, World!";
        Notification notification = new Notification(message);

        NotificationData data = notification.getData();
        assertNotNull(data);
        assertEquals(message, data.getMessage());
        assertNotNull(data.getDateSent());
    }

    @Test
    public void testGetMessage() {
        String message = "Hello, World!";
        Notification notification = new Notification(message);

        String actualMessage = notification.getData().getMessage();
        assertEquals(message, actualMessage);
    }

    @Test
    public void testGetDateSent() {
        String message = "Hello, World!";
        Notification notification = new Notification(message);

        assertNotNull(notification.getData().getDateSent());
    }

    @Test
    public void testGetNotificationDataNotNull() {
        String message = "Hello, World!";
        Notification notification = new Notification(message);

        NotificationData actualNotificationData = notification.getData();
        assertNotNull(actualNotificationData);
    }

    @Test
    public void testGetNotificationData() {
        String message = "Hello, World!";
        Notification notification = new Notification(message);

        NotificationData actualNotificationData = notification.getData();
        assertNotNull(actualNotificationData);
        assertEquals(message, actualNotificationData.getMessage());
        assertNotNull(actualNotificationData.getDateSent());
    }
}
