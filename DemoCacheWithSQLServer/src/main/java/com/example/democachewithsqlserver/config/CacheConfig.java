package com.example.democachewithsqlserver.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        Cache singlePostSeoUrlCache = new ConcurrentMapCache("user");
        Cache menuListCache = new ConcurrentMapCache("users");

        cacheManager.setCaches(Arrays.asList(singlePostSeoUrlCache, menuListCache));
        return cacheManager;
    }
}
