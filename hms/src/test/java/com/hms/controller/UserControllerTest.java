package com.hms.controller;

import com.hms.entity.User;
import com.hms.service.UserService;
import com.hms.controller.UserController;

import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.Test;
import org.mockito.*;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
//import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        user1 = new User(1L, "user1", "user1@example.com", "password1", true, "ROLE_USER", "male");
        user2 = new User(2L, "user2", "user2@example.com", "password2", true, "ROLE_USER", "female");
    }

    @Test
    public void testGetAllUsers(){
        when(userService.findAllUsers()).thenReturn(Arrays.asList(user1, user2));
        ResponseEntity<List<User>> response = userController.getAllUsers();

        try{
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(2, Objects.requireNonNull(response.getBody()).size());
            verify(userService, times(1)).findAllUsers();
            System.out.println("Controller: Find all users test passed!");
        } catch (Exception e){
            System.out.println("Controller: Test failed, " + e);
        }
    }

    @Test
    public void testGetUserById(){
        when(userService.findUserById(1L)).thenReturn(user1);
        ResponseEntity<User> response = userController.getUserById(1L);
        try{
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(user1, response.getBody());
            verify(userService, times(1)).findUserById(1L);
            System.out.println("Controller: Find user by ID test passed!");
        } catch (Exception ex){
            System.out.println("Controller: Test failed " + ex);
        }
    }

    @Test
    public void testGetUserByUserName(){
        when(userService.findUserByUsername("username2")).thenReturn(Optional.of(user2));
        ResponseEntity<User> response = userController.getUserByUsername("username2");
        try{
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(user2, response.getBody());
            verify(userService, times(1)).findUserByUsername("username2");
            System.out.println("Controller: Find user by username test passed!");
        } catch (Exception exp){
            System.out.println("Controller: Test failed "+ exp);
        }
    }

    @Test
    public void testCreateUser(){
        when(userService.isUserExists(user2.getId())).thenReturn(false);
        when(userService.saveUser(user2)).thenReturn(user2);
        ResponseEntity<User> saved = userController.createUser(user2);
        try{
            assertEquals(HttpStatus.CREATED, saved.getStatusCode());
            assertEquals(user2, saved.getBody());
            verify(userService, times(1)).isUserExists(user2.getId());
            verify(userService, times(1)).saveUser(user2);
            System.out.println("Controller: Save user test passed!");
        } catch (Exception e){
            System.out.println("Controller: Test failed " + e);
        }
    }

    @Test
    public void testUpdateUser() {
        when(userService.isUserExists(user1.getId())).thenReturn(true);
        when(userService.saveUser(user1)).thenReturn(user1);

        ResponseEntity<User> response = userController.updateUser(1L, user1);
        try {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(user1, response.getBody());
            verify(userService, times(2)).isUserExists(user1.getId());
            verify(userService, times(1)).saveUser(user1);
            System.out.println("Controller: Update user test passed!");
        } catch (Exception e){
            System.out.println("Controller: Test failed " + e);
        }
    }

    @Test
    public void testDeleteUserById() {
        doNothing().when(userService).deleteUserById(1L);
        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService, times(1)).deleteUserById(1L);
        System.out.println("Controller: Delete user by ID test passed!");
    }

}
