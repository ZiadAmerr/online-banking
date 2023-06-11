package source.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import source.src.Notification;
import source.src.NotificationData;

import static org.junit.Assert.*;

public class NotificationTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
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
}