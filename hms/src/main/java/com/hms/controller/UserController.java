package com.hms.controller;

import com.hms.entity.User;
//import com.hms.entity.UserLoginRequest;
import com.hms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.authentication.AuthenticationManager;


import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>>getAllUsers(){
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("user/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.findUserByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<User>> getUserByGender(@PathVariable String gender){
        List<User> users = userService.findByGender(gender);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUserByRole(@PathVariable String role){
        List<User> users = userService.findByRole(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        if(userService.isUserExists(user.getId())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        User createdUser = userService.saveUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<String> authenticateUser(@RequestBody UserLoginRequest userLoginRequest) {
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword());
//        Authentication authenticated = authenticationManager.authenticate(authentication);
//
//        SecurityContextHolder.getContext().setAuthentication(authenticated);
//
//        return new ResponseEntity<>("Authenticated successfully", HttpStatus.OK);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user){
        if((!userService.isUserExists(id)) || (!userService.isUserExists(user.getId()))){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if(id != user.getId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setId(id);
        User updatedUser = userService.saveUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
