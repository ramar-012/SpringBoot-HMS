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

import javax.transaction.Transactional;
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
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseURL;

    @BeforeEach
    @Transactional
    public void setUp() {
        baseURL = "http://localhost:" + port + "/api/users";
        userRepository.deleteAll();

        //users for testing
        userRepository.saveAndFlush(new User(5, "user5", "user5@example.com", "password1", true, "ROLE_USER", "male"));
        userRepository.saveAndFlush(new User(6, "staff6", "staff6@example.com", "password1", true, "ROLE_STAFF", "female"));
    }

    @Test
    public void testGetAllUsers() {
        try {
            ResponseEntity<User[]> response = restTemplate.getForEntity(baseURL, User[].class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            List<User> users = List.of(Objects.requireNonNull(response.getBody()));
            assertNotNull(users);
            assertEquals(2, users.size());
            System.out.println("CIT Get all users test passed!");
        } catch (Exception e) {
            System.out.println("CIT Get all users: Test failed " + e);
            // Optionally, rethrow the exception to ensure the test fails
            throw e;
        }
    }


    @Test
    public void testGetUserById(){
        ResponseEntity<User> response = restTemplate.getForEntity(baseURL+ "/5", User.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        try{
            User user = response.getBody();
            assertNotNull(user);
            assertEquals("user5", user.getUsername());
            System.out.println("CIT Get user by ID test passed!\n" + user.toString());
        } catch (Exception e){
            System.out.println("CIT Get all user by ID test failed " + e);
        }

    }
}