package com.example.demo.init;

import com.example.demo.model.dto.UserDetailAuthDto;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.example.demo.constants.Constants.ROLE_ADMIN;

@Component
@Order(0)
public class InitDatabase implements ApplicationListener<ApplicationReadyEvent> {


    private final RMapCache<String, UserDetailAuthDto> registerUsers;
    private final BCryptPasswordEncoder passwordEncoder;

    public InitDatabase(RMapCache<String, UserDetailAuthDto> registerUsers, BCryptPasswordEncoder passwordEncoder) {
        this.registerUsers = registerUsers;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(!this.registerUsers.containsKey("admin@gmail.com")){
            UserDetailAuthDto user = new UserDetailAuthDto(UUID.randomUUID().toString(),"admin@gmail.com",
                    passwordEncoder.encode("password"), List.of(ROLE_ADMIN));
            this.registerUsers.put("admin@gmail.com",user);
        }
    }
}
