package com.example.Notification.controller;

import com.example.Notification.dto.EmailRequest;
import com.example.Notification.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final EmailService emailService;

    @Autowired
    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        try {
            emailService.sendCustomEmail(request.getToEmail(), request.getSubject(), request.getText());
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        }
    }

    @PostMapping("/user-created")
    public ResponseEntity<String> notifyUserCreated(@RequestBody EmailRequest request) {
        try {
            emailService.sendUserCreationEmail(request.getToEmail());
            return ResponseEntity.ok("Creation email sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send creation email: " + e.getMessage());
        }
    }

    @PostMapping("/user-deleted")
    public ResponseEntity<String> notifyUserDeleted(@RequestBody EmailRequest request) {
        try {
            emailService.sendUserDeletionEmail(request.getToEmail());
            return ResponseEntity.ok("Deletion email sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send deletion email: " + e.getMessage());
        }
    }
}