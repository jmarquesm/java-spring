package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public UserDTO searchUserByName(String name) {
        User user = userRepository.findByName(name).get();
        UserDTO userDTO = objectMapper.convertValue(user, new TypeReference<UserDTO>() {
        });
        return userDTO;
    }

    @Override
    public UserDTO updateUserById(Long id, String name){
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            user.get().setName(name);
            userRepository.save(user.get());
            return objectMapper.convertValue(user, new TypeReference<UserDTO>() {
            });
        }
        return null;
    }

    @Override
    public void deleteUserById(Long id){
        Optional<User> user = userRepository.findById(id);

        user.ifPresent(value -> userRepository.delete(value));
    }

    @Override
    public UserDTO insertNewUser(UserDTO userDTO){
        User user = User.builder()
                .name(userDTO.getName())
                .build();
        userRepository.save(user);

        return objectMapper.convertValue(user, new TypeReference<UserDTO>() {
        });
    }

    @Override
    public List<UserDTO> listUsers() {
        List<User> user = userRepository.findAll();
        return objectMapper.convertValue(user, new TypeReference<List<UserDTO>>() {
        });
    }

}
