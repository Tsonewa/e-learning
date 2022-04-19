package com.example.demo.stream;

import org.springframework.cloud.stream.annotation.*;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.springframework.messaging.SubscribableChannel;

import static com.example.demo.constants.Constants.*;
import static com.example.demo.constants.Constants.COMPANY_OWNER_INITIALIZED;

@Component
public interface ProfileStream {

    @Output(EMPLOYEE_INFO)
    MessageChannel outboundEmployeeInfo();

    @Output(USER_PASSWORD_CHANGE)
    MessageChannel outEmployeePasswordChange();

    @Input(USER_INITIALIZED)
    SubscribableChannel getUserInitializerStreamInputSubscribableChannel();

    @Input(EMPLOYEE_INITIALIZED)
    SubscribableChannel getEmployeeInitializerStreamInputSubscribableChannel();


    @Input(COMPANY_NAME_INFO)
    SubscribableChannel getCompanyNameCheck();

    @Output(COMPANY_NAME_CHECK)
    MessageChannel outboundCompanyNameCheck();

    @Input(EMPLOYEE_COMPANY_OWNER)
    SubscribableChannel searchCompanyOwnerEmailSubscribableChannel();

    @Output(COMPANY_OWNER_INITIALIZED)
    MessageChannel outCompanyOwnerInfo();

    @Output(ADD_OWNER_TO_COURSE_OUT)
    MessageChannel outBusinessOwnerInfo();
}
