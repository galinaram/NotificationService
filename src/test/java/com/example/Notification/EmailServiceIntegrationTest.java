package com.example.Notification;

import com.example.Notification.service.EmailService;
import com.example.Notification.service.KafkaProducerService;
import com.example.Notification.service.UserEventConsumer;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
@ActiveProfiles("test")
class EmailServiceIntegrationTest {

    @Autowired
    private EmailService emailService;

    @MockBean
    private KafkaProducerService kafkaProducerService;

    @MockBean
    private UserEventConsumer userEventConsumer;

    @MockBean
    private JavaMailSender mailSender; // Мокаем JavaMailSender

    @Test
    void testSendUserCreationEmail() {
        System.out.println("=== Testing user creation email ===");

        doNothing().when(mailSender).send((MimeMessage) any());

        assertDoesNotThrow(() -> {
            emailService.sendUserCreationEmail("test@example.com");
        });
        System.out.println("User creation email test passed");
    }

    @Test
    void testSendUserDeletionEmail() {
        System.out.println("=== Testing user deletion email ===");

        doNothing().when(mailSender).send((MimeMessage) any());

        assertDoesNotThrow(() -> {
            emailService.sendUserDeletionEmail("test@example.com");
        });
        System.out.println("User deletion email test passed");
    }
}