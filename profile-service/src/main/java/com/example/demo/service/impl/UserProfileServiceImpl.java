package com.example.demo.service.impl;

import com.example.demo.error.UserNotFound;
import com.example.demo.model.binding.*;
import com.example.demo.model.entity.CompanyEntity;
import com.example.demo.model.entity.UserProfileEntity;
import com.example.demo.model.view.CountEmployeesView;
import com.example.demo.model.view.EmployeesByCompanyOwnerView;
import com.example.demo.model.view.UserProfileView;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.EmailService;
import com.example.demo.service.PasswordManagementService;
import com.example.demo.service.UserProfileService;
import com.example.demo.stream.ProfileStream;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.demo.constants.Constants.*;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    private final ProfileStream profileStream;
    private final UserProfileRepository userProfileRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final PasswordManagementService passwordManagementService;
    private final CloudinaryService cloudinaryService;

    public UserProfileServiceImpl(ProfileStream profileStream, UserProfileRepository userProfileRepository,
                                  CompanyRepository companyRepository, ModelMapper modelMapper,
                                  PasswordEncoder passwordEncoder, EmailService emailService,
                                  PasswordManagementService passwordManagementService,
                                  CloudinaryService cloudinaryService) {
        this.profileStream = profileStream;
        this.userProfileRepository = userProfileRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.passwordManagementService = passwordManagementService;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public <Т> void sendMessageOutBoundEmployeeInfo(Т kafkaModel) {
        MessageChannel messageChannel = this.profileStream.outboundEmployeeInfo();
        messageChannel.send(MessageBuilder
                .withPayload(kafkaModel)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

    @Override
    public <Т> void sendMessageOutBoundEmployeePasswordChange(Т kafkaModel) {
        MessageChannel messageChannel = this.profileStream.outEmployeePasswordChange();
        messageChannel.send(MessageBuilder
                .withPayload(kafkaModel)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

    @Override
    public <Т> void sendMessageOutBoundBusinessOwnerInfo(Т kafkaModel) {
        MessageChannel messageChannel = this.profileStream.outBusinessOwnerInfo();
        messageChannel.send(MessageBuilder
                .withPayload(kafkaModel)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

    @Override
    public void createUserProfile(UserProfileCreateBindingModel userProfile) {
        UserProfileEntity companyOwner = new UserProfileEntity();
        CompanyEntity companyNewEntity = new CompanyEntity();

        companyOwner.setEmail(userProfile.getEmail());
        companyOwner.setFullName(userProfile.getFullName());

        companyNewEntity
                .setEmail(userProfile.getEmail())
                .setName(userProfile.getCompanyName())
                .setCompanyOwner(userProfile.getEmail());

        this.companyRepository.save(companyNewEntity);
        companyOwner.setCompany(companyNewEntity);


        this.userProfileRepository.save(companyOwner);

        List<UserProfileEntity> company = new ArrayList<>();
        company.add(companyOwner);
        companyNewEntity.setUserProfile(company);

        this.companyRepository.save(companyNewEntity);

        BussinesOwnerOutCourse userInfo = new BussinesOwnerOutCourse(userProfile.getEmail());
        this.sendMessageOutBoundBusinessOwnerInfo(userInfo);

    }


    @Override
    public UserProfileView getUserProfileById(String id) throws UserNotFound {
        UserProfileEntity user = this.userProfileRepository.findById(id).orElseThrow(UserNotFound::new);

        return modelMapper.map(user, UserProfileView.class);
    }

    @Override
    public UserProfileView getUserProfileByEmail(String userEmail) throws UserNotFound {
        if (this.userProfileRepository.findByEmail(userEmail).isEmpty()) {
            throw new UserNotFound("no such user");
        }
        UserProfileEntity userEntity = this.userProfileRepository.findByEmail(userEmail).get();
        UserProfileView user = this.modelMapper.map(userEntity, UserProfileView.class);
        user.setCompany(userEntity.getCompany().getName());
        return user;
    }

    @Override
    public void getCompanyOwnerEmail(String employeesEmail) {
        CompanyOwnerEmail emailCompany = new CompanyOwnerEmail();
        emailCompany.setEmailEmployee(employeesEmail);

        CompanyEntity company = this.companyRepository.findCompanyEntityByEmail(employeesEmail).get();
        String emailCompanyOwner = company.getCompanyOwner();
        emailCompany.setEmailCompanyOwner(emailCompanyOwner);

        this.sendMessageOutBoundCompanyOwnerInfo(emailCompany);
    }

    @Override
    public String getEmployeeCompanyOwnerEmail(String employeeEmail) {
        UserProfileEntity user = this.userProfileRepository.findByEmail(employeeEmail).get();
        CompanyEntity company = user.getCompany();
//        CompanyEntity company = this.companyRepository.findCompanyEntityByEmail(employeeEmail).get();
        return company.getCompanyOwner();
    }


    @Override
    public List<EmployeeBindingModel> getAllEmployeesByBusinessOwner(HttpServletRequest request, int page, int limit) {
        String companyOwnerEmail = request.getHeader("X-User-Email");

        String companyId = this.companyRepository.getCompanyId(companyOwnerEmail);

        page--;
        Pageable pageableRequest = PageRequest.of(page, limit);

        return this.userProfileRepository.getEmployeesByBusinessOwner(companyId, companyOwnerEmail, pageableRequest)
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeBindingModel.class))
                .collect(Collectors.toList());


    }


    public <Т> void sendMessageOutBoundCompanyOwnerInfo(Т kafkaModel) {
        MessageChannel messageChannel = this.profileStream.outCompanyOwnerInfo();
        messageChannel.send(MessageBuilder
                .withPayload(kafkaModel)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }


    @Override
    @Transactional
    public UserProfileView editUserProfile(String email, UserProfileEditBindingModel userProfile, String password) throws IOException {

        UserProfileEntity userEntity = this.userProfileRepository.findByEmail(email).get();

        if (userProfile.getPicture()!=null) {
            pictureManage(userProfile, userEntity);
        }

        userEntity.setSummary(userProfile.getSummary());
        userEntity.setFullName(userProfile.getFullName());

        UserProfileEntity userProfileEntity = this.userProfileRepository.save(userEntity);

        if (password != null) {
            EmployeePasswordChangeBindingModel employee = new EmployeePasswordChangeBindingModel();
            employee.setEmail(email)
                    .setPassword(userProfile.getPassword());
            this.sendEmployeePassword(employee);
        }

        return this.modelMapper.map(userEntity, UserProfileView.class);
    }

    private void pictureManage(UserProfileEditBindingModel userProfile, UserProfileEntity userEntity) throws IOException {
        picControl(userProfile, userEntity);
    }

    private void picControl(UserProfileEditBindingModel userProfile, UserProfileEntity userEntity) throws IOException {
        if (userProfile.getPicture() != null) {
            if (userEntity.getPicture() != null) {
                deletePicture(userProfile, userEntity);
            } else {
                uploadPicture(userProfile, userEntity);
            }
        }
    }

    private void deletePicture(UserProfileEditBindingModel userProfile, UserProfileEntity userEntity) throws IOException {
        this.cloudinaryService.deleteImage(idPic(userEntity), Collections.emptyMap());
        uploadPicture(userProfile, userEntity);
    }

    private String idPic(UserProfileEntity userEntity) {
        return userEntity.getPicture()
                .substring(userEntity.getPicture().lastIndexOf(CLOUDINARY_CUT_START) + CLOUDINARY_CUT_INDEX,
                        userEntity.getPicture().lastIndexOf(CLOUDINARY_CUT_END));
    }

    private void uploadPicture(UserProfileEditBindingModel userProfile, UserProfileEntity userEntity) throws IOException {
        MultipartFile image = userProfile.getPicture();
        String imageUrl = cloudinaryService.uploadImage(image);
        userEntity.setPicture(imageUrl);
    }

    @Override
    public void createEmployee(EmployeeReceiveBindingModelList employees) {

        for (EmployeeReceiveBindingModel employee : employees.getEmployees()) {
            CompanyEntity currentCompany = this.companyRepository
                    .findCompanyEntityByCompanyOwner(employee.getCompanyOwner()).orElseThrow();
            UserProfileEntity currentEmployee = new UserProfileEntity();
            currentEmployee
                    .setCompany(currentCompany)
                    .setFullName(employee.getFullName())
                    .setEmail(employee.getEmail());

            this.userProfileRepository.save(currentEmployee);

            List<UserProfileEntity> allEmployees = currentCompany.getUserProfile();
            allEmployees.add(currentEmployee);
            currentCompany.setUserProfile(allEmployees);
            this.companyRepository.save(currentCompany);


            String decodePassword = this.passwordManagementService.decodePassword(employee.getPassword());

            sendMail(decodePassword, employee.getEmail());
        }
    }

    @Override
    public void sendEmployeePassword(EmployeePasswordChangeBindingModel employee) {

        EmployeePasswordChangeOut currentEmployee = modelMapper.map(employee, EmployeePasswordChangeOut.class);

        currentEmployee.setPassword(this.passwordEncoder.encode(employee.getPassword()));

        this.sendMessageOutBoundEmployeePasswordChange(currentEmployee);

    }

    @Override
    public boolean findByEmailsEmployees(EmployeeBindingModelList employees) {

        for (EmployeeBindingModel employee : employees.getEmployees()) {

            boolean isExist = this.userProfileRepository.existsByEmail(employee.getEmail());

            if (!isExist) {
                return false;
            }
        }

        return true;
    }

    @Override
    public List<EmployeesByCompanyOwnerView> getAllEmployeesByCompanyOwner(String companyOwner, int page, int limit) {

        if (page > 0) {
            page = page - 1;
        }

        Pageable pageableRequest = PageRequest.of(page, limit);

        return this.userProfileRepository.
                findAllByCompany_CompanyOwner(companyOwner, pageableRequest)
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeesByCompanyOwnerView.class))
                .sorted((a, b) -> a.getFullName().compareToIgnoreCase(b.getFullName()))
                .collect(Collectors.toList());
    }


    @Override
    public CountEmployeesView getEmployeesCountByCompany(HttpServletRequest request) {
        String companyOwnerEmail = request.getHeader("X-User-Email");
        String companyId = this.companyRepository.getCompanyId(companyOwnerEmail);
        int count = this.userProfileRepository.getCountEmployeesByCompany(companyId, companyOwnerEmail);
        return new CountEmployeesView(count);
    }


    private void sendMail(String password, String userMail) {
        String subject = "Password for login";

        String content = "This is your password: "
                + password + " Login here... Please,change your password at first login.";

        this.emailService.sendMessageWithAttachment(userMail, subject, content);
    }

    private String generatePassword() {

        int length = 8;
        String symbol = "-/.^&*_!@%=+>)";
        String cap_letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String small_letter = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String finalString = cap_letter + small_letter +
                numbers + symbol;

        Random random = new Random();

        char[] password = new char[length];

        for (int i = 0; i < length; i++) {
            password[i] = finalString.charAt(random.nextInt(finalString.length()));
        }

        return Arrays.toString(password);
    }
}
