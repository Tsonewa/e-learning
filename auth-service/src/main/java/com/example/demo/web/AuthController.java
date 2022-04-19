package com.example.demo.web;

import static com.example.demo.constants.Constants.*;

import com.example.demo.exceptions.*;
import com.example.demo.model.dto.*;
import com.example.demo.service.AuthService;
import com.example.demo.service.PasswordManagementService;
import com.example.demo.stream.StreamChannelDispatcher;
import com.example.demo.exceptions.UserRegisterParametersNotValidException;
import com.example.demo.exceptions.UserLoginOrPasswordNotValidException;
import org.modelmapper.ModelMapper;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;


@RequestMapping("/auth")
@RestController
public class AuthController {

    private final RMapCache<String, UserDetailAuthDto> registerUsers;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final ModelMapper modelMapper;
    private final StreamChannelDispatcher streamChannelDispatcher;
    private final PasswordManagementService passwordManagementService;

    public AuthController(RedissonClient redissonClient, BCryptPasswordEncoder passwordEncoder, AuthService authService, ModelMapper modelMapper, StreamChannelDispatcher streamChannelDispatcher, PasswordManagementService passwordManagementService) {
        this.registerUsers = redissonClient.getMapCache(REGISTER_USERS_MAP);
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.streamChannelDispatcher = streamChannelDispatcher;
        this.passwordManagementService = passwordManagementService;
    }

    @PostMapping("/token")
    public ResponseEntity<UserLoginDto> login(@RequestBody @Valid UserLoginDto userLoginDto, BindingResult bindingResult,
                                              HttpServletResponse httpServletResponse)
            throws UnauthorizedAccessException, UserNotFound {

        if (bindingResult.hasErrors()) {
            throw new UserLoginOrPasswordNotValidException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        Optional<UserDetailAuthDto> user = Optional.ofNullable(registerUsers.get(userLoginDto.getEmail()));

        if (user.isPresent() && !passwordEncoder.matches(userLoginDto.getPassword(), user.get().getPassword())) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new UnauthorizedAccessException("Unauthorized login due to wrong credentials!");
        }

        if (user.isEmpty()) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new UserNotFound("User with email " + userLoginDto.getEmail() + " do not exist!");
        }

        this.authService.loginUser(httpServletResponse, user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterDto userRegisterDto,
                                      BindingResult bindingResult,
                                      HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, CompanyDuplicationException {

        if (bindingResult.hasErrors()) {
            throw new UserRegisterParametersNotValidException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        if (this.registerUsers.containsKey(userRegisterDto.getEmail())) {
            throw new UserDuplicationException(userRegisterDto.getEmail());
        }

        UserRegisterCheckCompanyDto userTransfer = modelMapper.map(userRegisterDto, UserRegisterCheckCompanyDto.class);
        userTransfer.setPassword(passwordManagementService.encodePassword(userRegisterDto.getPassword()));

        this.streamChannelDispatcher.sendMessage(userTransfer, COMPANY_NAME_INFO);

        return ResponseEntity.status(202).body(userRegisterDto);//202 accepted
    }

    @PostMapping("/checkUserExist")
    public ResponseEntity<?> isUserExist(@RequestBody String email) {
        if (this.registerUsers.containsKey(email)) {
            return ResponseEntity.status(201).body(email);
        }
        return ResponseEntity.status(201).body(null);
    }

    @StreamListener(EMPLOYEE_REGISTER_IN)
    public void registerEmployees(@Payload EmployeeBindingModelList employeeBindingModelList)
            throws InvocationTargetException, IllegalAccessException {

        this.authService.registerEmployees(employeeBindingModelList.getEmployees());
    }

    @StreamListener(USER_PASSWORD_CHANGE_IN)
    public void changeUserPassword(@Payload UserPasswordChangeDto userPasswordChangeDto) {
        this.authService.changeEmployeePassword(userPasswordChangeDto);
    }
}
