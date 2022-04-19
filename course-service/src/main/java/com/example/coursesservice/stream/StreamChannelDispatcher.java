package com.example.coursesservice.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class StreamChannelDispatcher {

    private final CoursesStream coursesStream;

    public StreamChannelDispatcher(CoursesStream coursesStream) {
        this.coursesStream = coursesStream;
    }

    public <Т> void sendMessage(Т kafkaModel, String channelName) throws InvocationTargetException, IllegalAccessException {
        this.fetchChannel(channelName).send(MessageBuilder
                .withPayload(kafkaModel)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

    private MessageChannel fetchChannel(String channelName) throws InvocationTargetException, IllegalAccessException {
        Method method = Arrays.stream(CoursesStream.class.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Output.class))
                .filter(m -> m.getAnnotation(Output.class).value().equals(channelName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Stream channel is not found"));

        return (MessageChannel) method.invoke(this.coursesStream);
    }
}
