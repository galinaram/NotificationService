package com.example.Notification;

import com.example.Notification.dto.UserEvent;
import com.example.Notification.service.EmailService;
import com.example.Notification.service.UserEventConsumer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserEventConsumerTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserEventConsumer userEventConsumer;

    @Test
    void testConsumeUserCreateEvent() {
        // Given
        UserEvent event = new UserEvent("CREATE", "test@example.com", 1L);
        doNothing().when(emailService).sendUserCreationEmail("test@example.com");

        // When
        userEventConsumer.consumeUserEvent(event);

        // Then
        verify(emailService, times(1)).sendUserCreationEmail("test@example.com");
        System.out.println("✓ User create event consumption test passed");
    }

    @Test
    void testConsumeUserDeleteEvent() {
        // Given
        UserEvent event = new UserEvent("DELETE", "test@example.com", 1L);
        doNothing().when(emailService).sendUserDeletionEmail("test@example.com");

        // When
        userEventConsumer.consumeUserEvent(event);

        // Then
        verify(emailService, times(1)).sendUserDeletionEmail("test@example.com");
        System.out.println("✓ User delete event consumption test passed");
    }
}