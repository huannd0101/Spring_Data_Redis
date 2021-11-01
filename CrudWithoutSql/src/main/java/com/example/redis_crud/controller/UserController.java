package com.example.redis_crud.controller;

import com.example.redis_crud.entity.User;
import com.example.redis_crud.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao dao;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(200).body(dao.findUserById(id));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.status(200).body(dao.getAllUser());
    }

    @PostMapping("")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return ResponseEntity.status(201).body(dao.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(200).body(dao.deleteUserById(id));
    }

}
