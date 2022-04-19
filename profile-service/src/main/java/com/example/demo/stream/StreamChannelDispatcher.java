package com.example.demo.stream;

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

    private final ProfileStream profileStream;

    public StreamChannelDispatcher(ProfileStream profileStream) {
        this.profileStream = profileStream;
    }


    public void sendMessage(Object payload, String channelName) throws InvocationTargetException, IllegalAccessException {
        this.fetchChannel(channelName).send(MessageBuilder.withPayload(payload)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
    }

    private MessageChannel fetchChannel(String channelName) throws InvocationTargetException, IllegalAccessException {
        Method method = Arrays.stream(ProfileStream.class.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Output.class))
                .filter(m -> m.getAnnotation(Output.class).value().equals(channelName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such stream channel"));

        return (MessageChannel) method.invoke(this.profileStream);
    }
}
