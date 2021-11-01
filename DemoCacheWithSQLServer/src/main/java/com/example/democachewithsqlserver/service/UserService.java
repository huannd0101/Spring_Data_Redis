package com.example.democachewithsqlserver.service;

import com.example.democachewithsqlserver.dto.UserDTO;
import com.example.democachewithsqlserver.entity.User;
import com.example.democachewithsqlserver.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User getUserById(Integer id){
        System.out.println("Get user from DB");
        return userRepository.findById(id).get();
    }

    public List<User> getAllUser() {
        System.out.println("Get all user from DB");
        return userRepository.findAll();
    }

    public User createNewUser(UserDTO userDTO) {
        User user = new User(null, userDTO.getUsername(), userDTO.getPassword());
        return userRepository.save(user);
    }

    public User editUserById(Integer id, UserDTO userDTO) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new Exception("Not found");
        }
        User newUser = user.get();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());

        return userRepository.save(newUser);
    }

    public User deleteUserById(Integer id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new Exception("Not found");
        }
        userRepository.delete(user.get());
        return user.get();
    }

}
