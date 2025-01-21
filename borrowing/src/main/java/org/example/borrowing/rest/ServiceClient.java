package org.example.borrowing.rest;

import org.example.borrowing.dto.BookDTO;
import org.example.borrowing.dto.UserDTO;
import org.example.borrowing.dto.BorrowingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class ServiceClient {
    private final RestTemplate restTemplate;

    private static final String BOOK_SERVICE_URL = "http://book-service/books/";
    private static final String USER_SERVICE_URL = "http://user-service/users/";

    @Autowired
    public ServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getUserType(Long id) {
        String userServiceUrl = "http://localhost:8090/users/" + id;

        UserDTO userDTO = restTemplate.getForObject(userServiceUrl, UserDTO.class);

        if (userDTO != null) {
            return userDTO.getMembershipType();
        } else {
            throw new RuntimeException("Utilisateur avec l'ID " + id + " introuvable.");
        }
    }
}

