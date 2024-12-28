package com.future.springboot_restful_webservices.controller;

import com.future.springboot_restful_webservices.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.future.springboot_restful_webservices.entity.User;
import com.future.springboot_restful_webservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Build create User REST API
    // http://localhost:8080/api/users
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        logger.debug("Recieved user: {}", user);

        if(user.getFirstName() == null || user.getLastName() == null || user.getEmail() == null) {
            logger.debug("Bad request due to missing fields");
            return ResponseEntity.badRequest().body(null);
        }

        logger.debug("No missing field");
        UserDTO savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Build get user by id REST API
    // http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long userId) {
        UserDTO user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Build get all users REST API
    // http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Build Update User REST API
    // http://localhost:8080/api/users/1
    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody UserDTO userDTO) {
        userDTO.setId(userId);
        UserDTO updatedUser = userService.updateUser(userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Build delete User REST API
    // http://localhost:8080/api/users/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
}
