package com.hms.repository;
import com.hms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    //method to find a user by Id
    //check if user exists by id

    //find user by username
    //<Optional> User findByUsername(String username);
    Optional<User> findByUsername(String username);

    //find user by email
    User findByEmail(String email);

    //find by gender
    List<User> findByGender(String gender);

    //find by role
    List<User> findByRole(String role);
}
