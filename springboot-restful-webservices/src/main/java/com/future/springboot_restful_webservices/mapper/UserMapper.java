package com.future.springboot_restful_webservices.mapper;

import com.future.springboot_restful_webservices.dto.UserDTO;
import com.future.springboot_restful_webservices.entity.User;

public class UserMapper {

    // Convert User JPA Entity into UserDTO
    public static UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDTO;
    }

    // Convert UserDTO to User JPA Entity
    public static User mapToUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail()
        );
        return user;
    }
}
