package com.example.coursesservice.config;

import com.example.coursesservice.stream.CoursesStream;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(CoursesStream.class)
public class StreamConfiguration {
}
