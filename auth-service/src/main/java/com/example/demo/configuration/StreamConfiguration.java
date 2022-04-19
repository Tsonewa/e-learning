package com.example.demo.configuration;

import com.example.demo.stream.AuthStream;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(AuthStream.class)
public class StreamConfiguration {
}
