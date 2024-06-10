package com.hms.service;

import com.hms.entity.User;
import com.hms.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        user1 = new User(1L, "user1", "user1@example.com", "password1", true, "ROLE_USER", "male");
        user2 = new User(2L, "user2", "user2@example.com", "password2", true, "ROLE_USER", "female");
    }

    @Test
    public void testFindAllUsers(){
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        List<User> users = userService.findAllUsers();
        Object[] userX = users.toArray();
        try{
            assertEquals(2, users.size());
            verify(userRepository, times(1)).findAll();
            System.out.println("Service: Find all users test passed!");
        } catch (Exception e){
            System.out.println("Service: Test failed " + e);
        }
    }

    @Test
    public void testFindUserById(){
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        User foundUser = userService.findUserById(1L);
        try{
            assertEquals(user1.getUsername(), foundUser.getUsername());
            verify(userRepository, times(1)).findById(1L);
            System.out.println("Service: Find user by ID test passed!");
        } catch (Exception ex){
            System.out.println("Service: Test failed " + ex);
        }
    }

    @Test
    public void testFindUserByName(){
        when(userRepository.findByUsername("user1")).thenReturn(Optional.of(user1));
        Optional<User> foundUser = userService.findUserByUsername("user1");
        try{
            assertTrue(foundUser.isPresent());
            assertEquals(user1.getUsername(), foundUser.get().getUsername());
            verify(userRepository, times(1)).findByUsername("user1");
            System.out.println("Service: Find user by name test passed!");
        } catch (Exception e){
            System.out.println("Test failed "+ e);
        }
    }

    @Test
    public void testCreateUser(){
        when(userRepository.save(user1)).thenReturn(user1);
        User savedUser = userService.saveUser(user1);
        try{
            assertEquals(user1.getUsername() , savedUser.getUsername());
            verify(userRepository, times(1)).save(user1);
            System.out.println("Service: Create/Update user test passed!");
        } catch (Exception e){
            System.out.println("Service: Test failed "+ e);
        }
    }

    @Test
    public void testDeleteUserById() {
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteUserById(1L);
        verify(userRepository, times(1)).deleteById(1L);
        System.out.println("Service: Delete user by ID test passed!");
    }
}
