package com.example.redis_crud.repository;

import com.example.redis_crud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    public static final String HASH_KEY = "Users";

    @Autowired
    private RedisTemplate<String, Object> template;


    public User save(User user) {
        template.opsForHash().put(HASH_KEY, user.getId(), user);
        return user;
    }

    public List<Object> getAllUser() {
        return template.opsForHash().values(HASH_KEY);
    }

    public User findUserById(Integer id) {
        return (User) template.opsForHash().get(HASH_KEY, id);
    }

    public String deleteUserById(Integer id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "Removed";
    }

}
