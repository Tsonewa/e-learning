package com.example.demo.configuration;

import com.example.demo.model.dto.UserDetailAuthDto;
import org.redisson.Redisson;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.demo.constants.Constants.REGISTER_USERS_MAP;

@Configuration
public class RedisCacheConfig {


    @Bean
    public RedissonClient redisClient(@Value("${spring.redis.host}") String host, @Value("${spring.redis.port}") String port) {
        Config config = new Config();
        config.useSingleServer().setAddress(String.format("redis://%s:%s", host, port));
        return Redisson.create(config);
    }

    @Bean
    public RMapCache<String, UserDetailAuthDto> registerUsers(RedissonClient redissonClient) {
        return redissonClient.getMapCache(REGISTER_USERS_MAP);
    }


}
