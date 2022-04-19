package com.example.coursesservice.stream;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

import static com.example.coursesservice.constant.AppConstants.*;

public interface CoursesStream {

    @Output
    MessageChannel outboundBusinessOwnerGetEmployees();

    @Input(COMPANY_OWNER_EMPLOYEES)
    SubscribableChannel inboundBusinessOwnerEmployees();

    @Input(EMPLOYEES_CREATE_IN)
    SubscribableChannel inboundEmployeesCreate();

    @Input(BUSINESS_OWNER_CREATE_IN)
    SubscribableChannel inboundBusinessOwnerCreate();




}
