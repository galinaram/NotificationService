package com.example.Notification.controller;

import com.example.Notification.dto.UserRequestDTO;
import com.example.Notification.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {
    ResponseEntity<UserResponseDTO> createUser(UserRequestDTO userRequestDTO);
    ResponseEntity<UserResponseDTO> getUserById(Long id);
    ResponseEntity<List<UserResponseDTO>> getAllUsers();
    ResponseEntity<UserResponseDTO> updateUser(Long id, UserRequestDTO userRequestDTO);
    ResponseEntity<Void> deleteUser(Long id);
}