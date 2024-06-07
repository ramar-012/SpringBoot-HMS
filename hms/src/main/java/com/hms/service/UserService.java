package com.hms.service;

import com.hms.entity.User;
import com.hms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    //methods to be implemented
    //check if user exists by id
    public boolean isUserExists(long id) {
        return userRepository.existsById(id);
    }

    //method to save user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    //method to delete user by id
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    //method to find user by Id
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    //method to get all users
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    //find user by username
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //find by gender
    public List<User> findByGender(String gender){
        return userRepository.findByGender(gender);
    }

    //find by role
    public List<User> findByRole(String role){
        return userRepository.findByRole(role);
    }
}
