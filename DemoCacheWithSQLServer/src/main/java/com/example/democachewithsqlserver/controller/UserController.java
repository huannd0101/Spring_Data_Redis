package com.example.democachewithsqlserver.controller;

import com.example.democachewithsqlserver.dto.UserDTO;
import com.example.democachewithsqlserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    @Cacheable(value = "user", key = "#id")
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id)  {
        return ResponseEntity.status(200).body(service.getUserById(id));
    }

    @GetMapping
    @Cacheable(value = "users", key = "'all'")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.status(200).body(service.getAllUser());
    }

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(201).body(service.createNewUser(userDTO));
    }

    @PutMapping("/{id}")
    @CachePut(value = "user", key = "#userDTO.username")
    public ResponseEntity<?> editUserById(@PathVariable("id") Integer id, @RequestBody UserDTO userDTO) throws Exception {
        System.out.println("Edit");
        return ResponseEntity.status(200).body(service.editUserById(id, userDTO));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "user", key = "#id")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Integer id) throws Exception {
        System.out.println("Delete");
        return ResponseEntity.status(200).body(service.deleteUserById(id));
    }

    @CacheEvict("user")
    @GetMapping("/clear")
    public void clearCacheById(int id) {
    }

    @GetMapping("/clears")
    @CacheEvict(value = "user", allEntries = true)
    public void clearCache() {
    }
}
