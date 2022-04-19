package com.example.demo.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import static com.example.demo.constants.Constants.*;

@Component
public interface CoachStream {

    @Output(COACH_INFO)
    MessageChannel outboundCoachInfo();

    @Output(EMPLOYEE_COMPANY_OWNER)
    MessageChannel outboundEmployeeCompanyOwnerInfo();

    @Input(COMPANY_OWNER_INITIALIZED)
    SubscribableChannel getEmployeeInitializerStreamInputSubscribableChannel();


//    @Input(COMPANY_NAME_INFO)
//    SubscribableChannel getCompanyNameCheck();

    @Output(COMPANY_NAME_CHECK)
    MessageChannel outboundCompanyNameCheck();


}
