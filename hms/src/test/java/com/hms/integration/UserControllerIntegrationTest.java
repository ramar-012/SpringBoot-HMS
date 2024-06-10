package com.hms.integration;

import com.hms.entity.User;
import com.hms.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    private String baseURL;

    @BeforeEach
    public void setUp(){
        baseURL = "http://localhost:" + port + "/api/users";
        userRepository.deleteAll();

        //users for testing
        userRepository.save(new User(1L, "user1", "user1@example.com", "password1", true, "ROLE_USER", "male"));
        userRepository.save(new User(2L, "user2", "user2@example.com", "password2", true, "ROLE_USER", "female"));
    }

    @Test
    public void testGetAllUsers(){
        ResponseEntity<User [] > response = restTemplate.getForEntity(baseURL, User[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<User> users = List.of(Objects.requireNonNull(response.getBody()));
        assertNotNull(users);
        assertEquals(2, users.size());
    }
}
