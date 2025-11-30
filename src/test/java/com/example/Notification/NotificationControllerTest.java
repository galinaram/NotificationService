package com.example.Notification.controller;

import com.example.Notification.dto.EmailRequest;
import com.example.Notification.service.EmailService;
import com.example.Notification.service.KafkaProducerService;
import com.example.Notification.service.UserEventConsumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificationController.class)
@ActiveProfiles("test")
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmailService emailService;

    @MockBean
    private KafkaProducerService kafkaProducerService;

    @MockBean
    private UserEventConsumer userEventConsumer;

    @Test
    void testSendEmail() throws Exception {
        EmailRequest request = new EmailRequest();
        request.setToEmail("test@example.com");
        request.setSubject("Test Subject");
        request.setText("Test Message");

        doNothing().when(emailService).sendCustomEmail(anyString(), anyString(), anyString());

        mockMvc.perform(post("/api/notifications/send-email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Email sent successfully"));

        System.out.println("Send email API test passed");
    }

    @Test
    void testNotifyUserCreated() throws Exception {
        EmailRequest request = new EmailRequest();
        request.setToEmail("test@example.com");

        doNothing().when(emailService).sendUserCreationEmail(anyString());

        mockMvc.perform(post("/api/notifications/user-created")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Creation email sent successfully"));

        System.out.println("User created notification API test passed");
    }

    @Test
    void testNotifyUserDeleted() throws Exception {
        EmailRequest request = new EmailRequest();
        request.setToEmail("test@example.com");

        doNothing().when(emailService).sendUserDeletionEmail(anyString());

        mockMvc.perform(post("/api/notifications/user-deleted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Deletion email sent successfully"));

        System.out.println("User deleted notification API test passed");
    }
}