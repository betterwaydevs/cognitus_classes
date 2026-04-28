package com.example.helloworld.controller;

import com.example.helloworld.controller.AuthController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void loginWithValidCredentialsReturnsToken() {
        String url = "http://localhost:" + port + "/auth/login?username=admin&password=123456";
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains(".")); // JWT has dots
    }

    @Test
    void loginWithInvalidCredentialsThrowsException() {
        String url = "http://localhost:" + port + "/auth/login?username=wrong&password=wrong";
        assertThrows(Exception.class, () -> {
            restTemplate.postForEntity(url, null, String.class);
        });
    }
}
