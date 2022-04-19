package com.example.demo.controller;


import com.example.demo.error.DuplicateEmailEmployees;
import com.example.demo.error.IncorrectData;
import com.example.demo.error.UserNotFound;
import com.example.demo.error.UserWrongCredentials;
import com.example.demo.model.binding.*;
import com.example.demo.model.view.CompanyView;
import com.example.demo.model.view.CountEmployeesView;
import com.example.demo.model.view.EmployeesByCompanyOwnerView;
import com.example.demo.model.view.UserProfileView;
import com.example.demo.service.UserProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.constants.Constants.*;
import static com.example.demo.constants.Constants.EMPLOYEE_COMPANY_OWNER;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final ModelMapper modelMapper;

    public UserProfileController(UserProfileService userProfileService, ModelMapper modelMapper) {
        this.userProfileService = userProfileService;
        this.modelMapper = modelMapper;
    }

    @StreamListener(USER_INITIALIZED)
//    @PostMapping(value = "/owner",  produces = "application/json")
    public void handleUserRegisteredEvent(@Payload UserProfileCreateBindingModel userProfile) {

        this.userProfileService.createUserProfile(userProfile);

    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserProfile(HttpServletRequest request) throws UserNotFound {

        String userEmail = request.getHeader(USER_MAIL);

        UserProfileView currentUser = this.userProfileService.getUserProfileByEmail(userEmail);

        return ResponseEntity.ok(currentUser);
    }

    @PutMapping(value = "/edit")
    @ResponseBody
    public ResponseEntity<?> editUserProfile(
            @ModelAttribute UserProfileEditBindingModel userProfile,
            BindingResult bindingResult, HttpServletRequest request)
            throws IOException {

        String password = null;
        if (userProfile.getPassword() != null && userProfile.getConfirmPassword() != null) {
            if (userProfile.getPassword().equals(userProfile.getConfirmPassword())) {
                if (userProfile.getPassword().length() < 7) {
                    throw new UserWrongCredentials();
                }
            } else {
                throw new UserWrongCredentials();
            }
            password = userProfile.getPassword();
        }

        String userEmail = request.getHeader(USER_MAIL);

        UserProfileView editedUser = this.userProfileService.editUserProfile(userEmail, userProfile, password);

        return ResponseEntity.ok(editedUser);
    }

    @PostMapping(value = "/employee/create", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getEmployee(@RequestBody @Valid EmployeeBindingModelList employeeBindingModelList,
                                         BindingResult bindingResult, HttpServletRequest request) throws IncorrectData, DuplicateEmailEmployees {

        if (bindingResult.hasErrors()) {
            throw new IncorrectData();
        }

        boolean existEmployee = this.userProfileService.findByEmailsEmployees(employeeBindingModelList);

        if (existEmployee) {
            throw new DuplicateEmailEmployees();
        }
        String companyOwner = request.getHeader(USER_MAIL);


        List<EmployeeBindingModel> employeeBindingModels = employeeBindingModelList.getEmployees().stream()
                .map(e -> e.setCompanyOwner(companyOwner)).collect(Collectors.toList());

        EmployeeBindingModelList employees = new EmployeeBindingModelList();

        employees.setEmployees(employeeBindingModels);


        this.userProfileService.sendMessageOutBoundEmployeeInfo(employees);

        return ResponseEntity.ok(employees);
    }


    @StreamListener(EMPLOYEE_INITIALIZED)
    // input StreamListener Kafka @Input
//    @PostMapping("/employee/test")
//    @ResponseBody
    public void createEmployee(@Payload EmployeeReceiveBindingModelList employees) {

        this.userProfileService.createEmployee(employees);

//        return ResponseEntity.ok(isCreated);
    }

    @GetMapping("/employees")
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public ResponseEntity<?> getAllEmployees(HttpServletRequest request,
                                             @RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "limit", defaultValue = "4") int limit) {

        String companyOwner = request.getHeader("X-User-Email");


        List<EmployeesByCompanyOwnerView> employees = this.userProfileService.getAllEmployeesByCompanyOwner(companyOwner, page, limit);

        return ResponseEntity.ok().body(employees);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid EmployeePasswordChangeBindingModel employee,
                                            BindingResult bindingResult) throws IncorrectData {

        if (bindingResult.hasErrors() || !employee.passwordMatch()) {
            throw new IncorrectData();
        }

        this.userProfileService.sendEmployeePassword(employee);

        return ResponseEntity.status(201).build();
    }

    @StreamListener(EMPLOYEE_COMPANY_OWNER)
//    @PostMapping("/employee/test")
//    @ResponseBody
    public void searchCompanyOwnerEmail(@Payload String employeesEmail) {

        this.userProfileService.getCompanyOwnerEmail(employeesEmail);

//        return ResponseEntity.ok(isCreated);
    }

    @GetMapping("/employee/companyOwner")
    public ResponseEntity<?> searchCompanyOwnerEmail(HttpServletRequest http) {

        String employeeEmail = http.getHeader(USER_MAIL);

        String emailCompanyOwner = this.userProfileService.getEmployeeCompanyOwnerEmail(employeeEmail);

        return ResponseEntity.ok(emailCompanyOwner);
    }

    @GetMapping("/get/employees-by-company")
    public ResponseEntity<?> getAllEmployeesByBusinessOwner(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "3") int limit, HttpServletRequest request) {
        List<EmployeeBindingModel> employeesModel = this.userProfileService.getAllEmployeesByBusinessOwner(request, page, limit);
        EmployeeBindingModelList employees = new EmployeeBindingModelList(employeesModel);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/count/employees-by-company")
    public ResponseEntity<CountEmployeesView> getEmployeeCountByBusinessOwner(HttpServletRequest request) {
        CountEmployeesView count = this.userProfileService.getEmployeesCountByCompany(request);
        return ResponseEntity.ok(count);
    }

}
