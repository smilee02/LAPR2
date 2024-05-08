package app.domain.model;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class PasswordGenerationEmailSMSTest {

    @Test
    public void emailClientRegistration() throws IOException {
        assertNotNull(PasswordGenerationEmailSMS.emailClientRegistration("r@g.com"));
    }
    @Test
    public void sendNotification(){
        assertTrue(PasswordGenerationEmailSMS.notificationSending("hey@g.com"));
    }
}