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
    private User userProv;

    @BeforeEach
    @Transactional
    public void setUp() {
        baseURL = "http://localhost:" + port + "/api/users";
        userRepository.deleteAll();

        //users for testing
        userRepository.saveAndFlush(new User(5, "user5", "user5@example.com", "password5", true, "ROLE_USER", "male"));
        userRepository.saveAndFlush(new User(6, "staff6", "staff6@example.com", "password6", true, "ROLE_STAFF", "male"));
        userProv = new User(10, "testUser", "testUser@example.com", "passwordTest", true, "ROLE_USER", "female");
        userRepository.saveAndFlush(userProv);
        userRepository.saveAndFlush(new User(7, "staff7", "staff7@example.com", "password7", true, "ROLE_STAFF", "female"));
    }

    @Test
    public void testGetAllUsers() {
        try {
            ResponseEntity<User[]> response = restTemplate.getForEntity(baseURL, User[].class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            List<User> users = List.of(Objects.requireNonNull(response.getBody()));
            assertNotNull(users);
            assertEquals(4, users.size());
            System.out.println("CIT Get all users test passed!");
        } catch (Exception e) {
            System.out.println("CIT Get all users: Test failed, " + e);
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
            System.out.println("CIT Get user by ID test passed!");
        } catch (Exception e){
            System.out.println("CIT Get all user by ID test failed, " + e);
        }
    }

    @Test
    public void testUserByUserName(){
        ResponseEntity<User> response = restTemplate.getForEntity(baseURL + "/user/testUser", User.class);
        try{
            User userRes = response.getBody();
            assertNotNull(userRes);
            assertEquals(userProv.toString(), userRes.toString());
            System.out.println("CIT Get user by username test passed!");
        } catch (Exception e){
            System.out.println("CIT Get user by username test failed, " + e);
        }
    }

    @Test
    public void testUsersByGender(){
        ResponseEntity<User[]> response = restTemplate.getForEntity(baseURL + "/gender/female", User[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        try{
            List<User> users = List.of(Objects.requireNonNull(response.getBody()));
            assertNotNull(users);
            assertEquals(2, users.size());
            System.out.println("CIT Get user by gender test passed!");
        } catch (Exception e){
            System.out.println("CIT Get user by gender test failed, " + e);
        }
    }

    @Test
    public void testCreateUser(){
        User userToSave = new User(8, "user8", "user8@example.com", "password8", true, "ROLE_USER", "male");
        ResponseEntity<User> response = restTemplate.postForEntity(baseURL, userToSave, User.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        try{
            User savedUser = response.getBody();
            assertNotNull(savedUser);
            assertEquals(userToSave.toString(), savedUser.toString());
            System.out.println("CIT Save user test passed!");
        } catch (Exception e){
            System.out.println("CIT Save user test failed, " + e);
        }
    }

    @Test
    public void testUpdateUser(){
        User userToUpdate = new User(5, "user5", "user5@example.com", "password5user", true, "ROLE_USER", "male");
        restTemplate.put(baseURL + "/5", userToUpdate);

        ResponseEntity<User> response = restTemplate.getForEntity(baseURL + "/5", User.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        try{
            User updatedUser = response.getBody();
            assertNotNull(updatedUser);
            assertEquals(userToUpdate.toString(), updatedUser.toString());
            System.out.println("CIT Update user test passed!");
        } catch (Exception e){
            System.out.println("CIT Update user test failed, " + e);
        }
    }

    @Test
    public void testDeleteUser(){
        restTemplate.delete(baseURL + "/6");

        ResponseEntity<User> response = restTemplate.getForEntity(baseURL + "/6", User.class);
        try{
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            System.out.println("CIT Delete user test passed!");
        } catch (Exception e){
            System.out.println("CIT Delete user test failed, " + e);
        }
    }

}