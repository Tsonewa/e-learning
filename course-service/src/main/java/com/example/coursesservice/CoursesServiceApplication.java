package com.example.coursesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CoursesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoursesServiceApplication.class, args);
    }

}
