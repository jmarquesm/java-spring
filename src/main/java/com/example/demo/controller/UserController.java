package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userByName")
    public ResponseEntity<UserDTO> searchUserByName(@RequestParam String name){
        return new ResponseEntity<>(userService.searchUserByName(name), HttpStatus.OK);
    }

    @PutMapping("/updateUserById")
    public ResponseEntity<UserDTO> updateUserById(@RequestParam Long id, String name){
        return new ResponseEntity<>(userService.updateUserById(id, name), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserById")
    public ResponseEntity<Void> deleteUserById(@RequestParam Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/insertNewUser")
    public ResponseEntity<UserDTO> insertNewUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.insertNewUser(userDTO), HttpStatus.OK);
    }

    @GetMapping("/listUsers")
    public  ResponseEntity<List<UserDTO>> listUsers(){
        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }

}
