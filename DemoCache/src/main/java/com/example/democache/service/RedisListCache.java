package com.example.democache.service;

import com.example.democache.entity.User;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedisListCache {
    private ListOperations<String, Object> listOps;

    public RedisListCache(final RedisTemplate<String, Object> redisTemplate) {
        listOps = redisTemplate.opsForList();
    }

    //demo for user
    public void cacheList(final String key, final List<User> users) {
        for(User user : users) {
            listOps.leftPush(key, user);
        }
//        listOps.leftPushAll(key, users);
    }

    //xử dụng chỉ số(0-2: 3 phần từ)
    public List<User> getUserInRange(final String key, final int from, final int to) {
        final List<Object> objects = listOps.range(key, from, to);
        if(CollectionUtils.isEmpty(objects)) {
            return Collections.emptyList();
        }
        return objects.stream().map(x -> (User)x).collect(Collectors.toList());
    }

    //get xong xóa luôn :v
    public User getUserLastOfList(final String key) {
        final Object object = listOps.rightPop(key);
        if(object == null) {
            return null;
        }
        return (User) object;
    }

    //remove các phần tử ngoài khoảng truyền vào: index nhé
    public void trim(final String key, final int from, final int to) {
        listOps.trim(key, from, to);
    }
}
