package com.example.demo.configuration;

import com.example.demo.stream.CoachStream;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(CoachStream.class)
public class StreamConfiguration {
}
