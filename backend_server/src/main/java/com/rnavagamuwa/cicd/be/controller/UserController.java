package com.rnavagamuwa.cicd.be.controller;

import com.rnavagamuwa.cicd.be.exception.ResourceNotFoundException;
import com.rnavagamuwa.cicd.be.model.User;
import com.rnavagamuwa.cicd.be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author rnavagamuwa
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUserrs() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{userID}")
    public User getUserByID(@PathVariable Long userID) {
        return userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", "userID", userID));
    }

    @GetMapping("/users/ssn/{ssn}")
    public User getUserBySSN(@PathVariable String ssn) throws InterruptedException {
        Thread.sleep(10);
        User user = userRepository.findBySsn(ssn);
        if (user == null) {
            throw new ResourceNotFoundException("User", "ssn", ssn);
        }
        return user;
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        user.setIsVerified(false);
        return userRepository.save(user);
    }

    @PutMapping("/users/{userID}")
    public User updateUser(@PathVariable Long userID, @Valid @RequestBody User userDetails) {
        User user = userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", "userID", userID));
        user.setCity(userDetails.getCity());
        user.setDob(userDetails.getDob());
        user.setFullName(userDetails.getFullName());
        user.setSsn(userDetails.getSsn());

        return userRepository.save(user);
    }

    @DeleteMapping("/users/{userID}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userID) {
        User user = userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", "userID", userID));
        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/verify/{userID}")
    public ResponseEntity<?> verifyUser(@PathVariable Long userID) {
        User user = userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", "userID", userID));
        user.setIsVerified(true);
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
