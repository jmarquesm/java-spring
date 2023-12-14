package com.example.demo.service;

import com.example.demo.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO searchUserByName(String name);
    UserDTO updateUserById(Long id, String name);

    void deleteUserById(Long id);

    UserDTO insertNewUser(UserDTO userDTO);

    List<UserDTO> listUsers();
}