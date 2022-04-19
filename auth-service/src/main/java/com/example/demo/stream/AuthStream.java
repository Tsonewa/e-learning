package com.example.demo.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;

import static com.example.demo.constants.Constants.*;
import static com.example.demo.constants.Constants.COMPANY_NAME_INFO;

@Service
public interface AuthStream {

    @Output(BUSINESS_OWNER_INFO_OUT)
    MessageChannel outboundBOInfoToProfile();

    @Output(EMPLOYEE_REGISTER_OUT)
    MessageChannel outboundEmployeeFromProfile();

    @Input(EMPLOYEE_REGISTER_IN)
    SubscribableChannel inboundEmployeeRegisterFromProfile();

    @Input(USER_PASSWORD_CHANGE_IN)
    SubscribableChannel inboundUserPasswordChangeFromProfile();

    @Output(COMPANY_NAME_INFO)
    MessageChannel outboundCompanyNameInfoToProfile();

    @Input(COMPANY_NAME_CHECK)
    SubscribableChannel inboundCompanyNameCheckFromProfile();

}
