package com.example.demo.configuration;

import com.example.demo.stream.ProfileStream;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(ProfileStream.class)
public class StreamConfiguration {
}
