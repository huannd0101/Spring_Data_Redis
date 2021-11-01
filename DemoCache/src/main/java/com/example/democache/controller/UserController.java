package com.example.democache.controller;

import com.example.democache.entity.User;
import com.example.democache.service.RedisListCache;
import com.example.democache.service.RedisValueCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final RedisValueCache valueCache;
    private final RedisListCache listCache;
    private final String HASH_KEY = "user";

    @Autowired
    public UserController(final RedisValueCache valueCache, final RedisListCache listCache) {
        this.valueCache = valueCache;
        this.listCache = listCache;
    }

    @PostMapping
    public void cacheUser(@RequestBody final User dto) {
        valueCache.cache(HASH_KEY + dto.getId(), dto);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable final String id) {
        return (User) valueCache.getCacheValue(HASH_KEY + id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable final String id) {
        valueCache.deleteCacheValue(HASH_KEY + id);
    }

    @PostMapping("/list/{key}")
    public void cacheUsers(@PathVariable final String key, @RequestBody final List<User> users) {
        listCache.cacheList(key, users);
    }

    @GetMapping("/list/{key}/{from}/{to}")
    public List<User> getUserInRange(
            @PathVariable final String key,
            @PathVariable final int from,
            @PathVariable final int to
    ) {
        return listCache.getUserInRange(key, from, to);
    }

    @GetMapping("/list/last/{key}")
    public User getLastElement(@PathVariable final String key) {
        return listCache.getUserLastOfList(key);
    }

    @DeleteMapping("/list/{key}")
    public void trim(
            @PathVariable final String key,
            @PathVariable final int from,
            @PathVariable final int to
    ) {
        listCache.trim(key, from, to);
    }
}
