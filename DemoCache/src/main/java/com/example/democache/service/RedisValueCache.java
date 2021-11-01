package com.example.democache.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;


@Service
public class RedisValueCache {

    private final ValueOperations<String, Object> valueOps;

    public RedisValueCache(final RedisTemplate<String, Object> template) {
        this.valueOps = template.opsForValue();
    }

    public void cache(final String key, final Object data) {
        valueOps.set(key, data);
//        valueOps.set(key, data, 4000, TimeUnit.MILLISECONDS); //sau 4s thì sẽ tự clear
    }

    public Object getCacheValue(final String key) {
        return valueOps.get(key);
    }

    public void deleteCacheValue(final String key) {
        valueOps.getOperations().delete(key);
    }
}
