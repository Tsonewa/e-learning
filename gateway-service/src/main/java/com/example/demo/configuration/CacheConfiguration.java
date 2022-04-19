package com.example.demo.configuration;

import org.redisson.Redisson;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

import static com.example.demo.constant.Constants.*;

@Configuration
public class CacheConfiguration {
    private final String redisHost;
    private final String redisPort;

    public CacheConfiguration(@Value("${spring.redis.host}") String redisHost, @Value("${spring.redis.port}") String redisPort) {
        this.redisHost = redisHost;
        this.redisPort = redisPort;
    }

    @Bean
    public RedissonClient redisClient(){
        Config config = new Config();

        config.useSingleServer().setAddress(String.format("redis://%s:%s", redisHost, redisPort));

        RedissonClient redissonClient = Redisson.create(config);

        return redissonClient;
    }

    @Bean
    public RMapCache<String, Set<String>> roleMap(RedissonClient redissonClient){
        return redissonClient.getMapCache(ROLE_COLLECTION);
    }

}
