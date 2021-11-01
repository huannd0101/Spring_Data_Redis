package com.example.democachewithsqlserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication()
@EnableCaching
public class DemoCacheWithSqlServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoCacheWithSqlServerApplication.class, args);
    }

}
