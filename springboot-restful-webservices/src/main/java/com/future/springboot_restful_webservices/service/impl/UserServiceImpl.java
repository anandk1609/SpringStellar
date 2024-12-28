package com.future.springboot_restful_webservices.service.impl;

import com.future.springboot_restful_webservices.dto.UserDTO;
import com.future.springboot_restful_webservices.entity.User;
import com.future.springboot_restful_webservices.mapper.UserMapper;
import com.future.springboot_restful_webservices.repository.UserRepository;
import com.future.springboot_restful_webservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        //Convert UserDTO into User JPA Entity
        User user = UserMapper.mapToUser(userDTO);

        User newUser = userRepository.save(user);

        //Convert User JPA Entity to UserDTO
        UserDTO newUserDTO = UserMapper.mapToUserDTO(newUser);

        return newUserDTO;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return UserMapper.mapToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
