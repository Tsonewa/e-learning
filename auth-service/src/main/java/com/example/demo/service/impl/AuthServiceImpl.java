package com.example.demo.service.impl;

import com.example.demo.exceptions.CompanyDuplicationException;
import com.example.demo.model.dto.*;
import com.example.demo.service.AuthService;
import com.example.demo.service.JWTService;
import com.example.demo.service.PasswordManagementService;
import com.example.demo.stream.StreamChannelDispatcher;
import org.modelmapper.ModelMapper;
import org.redisson.api.RMapCache;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.demo.constants.Constants.*;

@Service
public class AuthServiceImpl implements AuthService {
    public static boolean isCompanyExist = false;

    private final JWTService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RMapCache<String, UserDetailAuthDto> registerUsers;
    private final ModelMapper modelMapper;
    private final StreamChannelDispatcher streamChannelDispatcher;
    private final PasswordManagementService passwordManagementService;


    public AuthServiceImpl(JWTService jwtService, BCryptPasswordEncoder passwordEncoder, RMapCache<String,
            UserDetailAuthDto> registerUsers, ModelMapper modelMapper, StreamChannelDispatcher streamChannelDispatcher, PasswordManagementService passwordManagementService) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.registerUsers = registerUsers;
        this.modelMapper = modelMapper;
        this.streamChannelDispatcher = streamChannelDispatcher;
        this.passwordManagementService = passwordManagementService;
    }

    public void loginUser(HttpServletResponse httpServletResponse, Optional<UserDetailAuthDto> user) {
        String token = this.jwtService.generateToken(user.get());
        httpServletResponse.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    public List<EmployeeRegisterOutDto> registerEmployees(List<EmployeeBindingModel> employeeBindingModels) throws InvocationTargetException, IllegalAccessException {
        List<EmployeeRegisterOutDto> employees = employeeBindingModels.stream().map(e -> {
            String currentRandomPassword = generateRandomPassword();

            UserDetailAuthDto employee = new UserDetailAuthDto()
                    .setEmail(e.getEmail())
                    .setPassword(this.passwordEncoder.encode(currentRandomPassword))
                    .setRoles(List.of(ROLE_EMPLOYEE));

            this.registerUsers.put(employee.getEmail(), employee);

            EmployeeRegisterOutDto employeeOut = modelMapper.map(employee, EmployeeRegisterOutDto.class);
            employeeOut.setFullName(e.getFullName());

            employeeOut.setCompanyOwner(e.getCompanyOwner());
            //employeeOut.setCompanyOwner("admin@gmail.com");

            String encodePassword = passwordManagementService.encodePassword(currentRandomPassword);

            employeeOut.setPassword(encodePassword);

            String decodePassword = passwordManagementService.decodePassword(encodePassword);

            return employeeOut;
        }).collect(Collectors.toList());

        this.streamChannelDispatcher.sendMessage
                (new EmployeeOutputListDto(employees), EMPLOYEE_REGISTER_OUT);

        return employees;
    }

    @Override
    public void changeEmployeePassword(UserPasswordChangeDto userPasswordChangeDto) {
        Optional<UserDetailAuthDto> employee = Optional.ofNullable(this.registerUsers.get(userPasswordChangeDto.getEmail()));
        if (employee.isEmpty()) {
            throw new NoSuchElementException("Employee not found");
        }
        employee.get().setPassword(userPasswordChangeDto.getPassword());
        this.registerUsers.put(userPasswordChangeDto.getEmail(), employee.get());
    }

    @Override
    public Optional<UserDetailAuthDto> businessOwnerRegister(UserRegisterDto userRegisterDto) throws InvocationTargetException, IllegalAccessException {

        UserDetailAuthDto userDetailAuthDto = new UserDetailAuthDto()
                .setEmail(userRegisterDto.getEmail())
                .setPassword(passwordEncoder.encode(userRegisterDto.getPassword()))
                .setRoles(List.of(ROLE_COMPANY));

        this.registerUsers.put
                (userRegisterDto.getEmail(), userDetailAuthDto);

        BusinessOwnerOutDto businessOwnerOutDto =
                modelMapper.map(userDetailAuthDto, BusinessOwnerOutDto.class);
        businessOwnerOutDto.setFullName(userRegisterDto.getFullName());
        businessOwnerOutDto.setCompanyName(userRegisterDto.getCompanyName());

        this.streamChannelDispatcher.sendMessage(businessOwnerOutDto, BUSINESS_OWNER_INFO_OUT);

        return Optional.ofNullable
                (this.registerUsers.get(userDetailAuthDto.getEmail()));
    }

    @StreamListener(COMPANY_NAME_CHECK)
    public void checkCompanyName(@Payload UserRegisterCheckCompanyDto userTransfer) throws CompanyDuplicationException, InvocationTargetException, IllegalAccessException {

        System.out.println("From listener");
        if (userTransfer.isCompanyExist()) {
            throw new CompanyDuplicationException();
        }
        UserRegisterDto userRegister = modelMapper.map(userTransfer, UserRegisterDto.class);
        userRegister.setPassword(passwordManagementService.decodePassword(userRegister.getPassword()));
        this.businessOwnerRegister(userRegister);

        //go to front-end
        //this.authService.loginUser(tempResponse, registeredUser);
    }

    private String generateRandomPassword() {

        int length = 8;
        String symbol = "-/.^&*_!@%=+>)";
        String cap_letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String small_letter = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String finalString = cap_letter + small_letter +
                numbers + symbol;

        Random random = new Random();

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            password.append(finalString.charAt(random.nextInt(finalString.length())));
        }
        return password.toString();
    }
}


