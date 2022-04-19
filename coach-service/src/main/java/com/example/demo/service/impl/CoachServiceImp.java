package com.example.demo.service.impl;

import com.example.demo.model.binding.CoachBindingAdminModel;
import com.example.demo.model.binding.CoachEditBindingModel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

import com.example.demo.model.binding.CoachFeedbackBindingModel;
import com.example.demo.model.binding.ResourceBindingModel;
import com.example.demo.model.binding.SeachCriteriaModels.CategoryBindingModel;
import com.example.demo.model.binding.SeachCriteriaModels.LanguageBindingModel;
import com.example.demo.model.binding.SeachCriteriaModels.SearchCriteria;
import com.example.demo.model.entity.*;
import com.example.demo.model.enums.Category;
import com.example.demo.model.enums.Language;
import com.example.demo.model.view.*;
import com.example.demo.repository.*;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.CoachService;
import com.example.demo.service.EmailService;
import com.example.demo.stream.CoachStream;
import org.modelmapper.ModelMapper;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.example.demo.constants.Constants.*;

@Service
public class CoachServiceImp implements CoachService {

    private final CoachRepository coachRepository;
    private final CoachDetailsRepository coachDetailsRepository;
    private final CoachStream coachStream;
    private final ModelMapper modelMapper;
    private final ResourceRepository resourceRepository;
    private final BusinessOwnerRepository businessOwnerRepository;
    private final FeedBackRepository feedBackRepository;
    private final LanguageRepository languageRepository;
    private final CategoryRepository categoryRepository;
    private final EmployeeCoachRepository employeeCoachRepository;
    private final CloudinaryService cloudinaryService;
    private final EmailService emailService;
    private final SessionRepository sessionRepository;


    public CoachServiceImp(CoachRepository coachRepository, CoachDetailsRepository coachDetailsRepository,
                           CoachStream coachStream, ModelMapper modelMapper, ResourceRepository resourceRepository,
                           BusinessOwnerRepository businessOwnerRepository, FeedBackRepository feedBackRepository,
                           LanguageRepository languageRepository, CategoryRepository categoryRepository,
                           EmployeeCoachRepository employeeCoachRepository, CloudinaryService cloudinaryService,
                           EmailService emailService, SessionRepository sessionRepository) {
        this.coachRepository = coachRepository;
        this.coachDetailsRepository = coachDetailsRepository;
        this.coachStream = coachStream;
        this.modelMapper = modelMapper;
        this.resourceRepository = resourceRepository;
        this.businessOwnerRepository = businessOwnerRepository;
        this.feedBackRepository = feedBackRepository;
        this.languageRepository = languageRepository;
        this.categoryRepository = categoryRepository;
        this.employeeCoachRepository = employeeCoachRepository;
        this.cloudinaryService = cloudinaryService;
        this.emailService = emailService;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<CoachViewModelByAdmin> getAllByAdmin() {
        List<CoachEntity> allCoaches = this.coachRepository.findAllCoaches();

        return allCoaches.stream().map((d) -> this.modelMapper.map(d, CoachViewModelByAdmin.class))
                .collect(Collectors.toList());
    }

    @Override
    public CoachViewModelId createCoachByAdmin(CoachBindingAdminModel coachBindingModel) {
        CoachEntity coach = this.modelMapper.map(coachBindingModel, CoachEntity.class);

        String video = coachBindingModel.getIntroductionVideo().substring(coachBindingModel.getIntroductionVideo().lastIndexOf("=") + 1);
        String videoUrl = "https://www.youtube.com/embed/" + video;

        List<ResourceBindingModel> resources = coachBindingModel.getResources();

        List<ResourceEntity> resourceEntities =
                resources.stream().map((m) ->
                                this.modelMapper.map(m, ResourceEntity.class))
                        .collect(Collectors.toList());

        this.resourceRepository.saveAll(resourceEntities);
        coach.setResources(resourceEntities);

        List<CategoryEntity> categoryList =
                coachBindingModel.getCategories().stream()
                        .map(c -> categoryRepository.findByCategory(Category.valueOf(c.getCategory()))).collect(Collectors.toList());
        coach.setCategories(categoryList);


        List<LanguageEntity> languageList = coachBindingModel.getLanguages().stream()
                .map((l) -> languageRepository.findByLanguage(Language.valueOf(l.getLanguage()))).collect(Collectors.toList());
        coach.setLanguages(languageList);

        CoachEntity savedCoach = this.coachRepository.save(coach);

        CoachDetailsEntity coachDetails = this.modelMapper.map(coachBindingModel, CoachDetailsEntity.class);

        coachDetails.setCoach(savedCoach);
        coachDetails.setIntroductionVideo(videoUrl);
        this.coachDetailsRepository.save(coachDetails);

        LocalDateTime startDate = coachBindingModel.getStartDate();
        LocalDateTime endDate = coachBindingModel.getEndDate();

        List<SessionEntity> coachSession = createSession(savedCoach, startDate, endDate);

        savedCoach.setDetails(coachDetails);
        savedCoach.setSessions(coachSession);
        CoachEntity save = this.coachRepository.save(savedCoach);

        return this.modelMapper.map(save, CoachViewModelId.class);

    }

    private List<SessionEntity> createSession(CoachEntity savedCoach, LocalDateTime startDate, LocalDateTime endDate) {
        List<SessionEntity> coachSession = new ArrayList<>();

        for (LocalDateTime date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            SessionEntity current = new SessionEntity();
            current.setSession(date);
            current.setCoach(savedCoach);
            current.setAvailable(true);
            SessionEntity save = this.sessionRepository.save(current);
            coachSession.add(save);
        }
        return coachSession;
    }

    @Override
    public void addPictureToCoach(String id, MultipartFile picture) throws IOException {
        CoachEntity currenCoach = this.coachRepository.findById(id).get();
        currenCoach.setPicture(this.cloudinaryService.uploadImage(picture));
        this.coachRepository.save(currenCoach);
    }

    @Override
    public List<SessionViewModel> getAllCoachSession(String id) {
        return this.sessionRepository.findAllByCoach_Id(id).stream()
                .map(m -> this.modelMapper.map(m, SessionViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CoachDetailsViewModel findCoachDetails(String id) {
        CoachEntity coach = this.coachRepository.findById(id).get();
        CoachDetailsEntity details = coach.getDetails();
        int size = coach.getResources().size();
        CoachDetailsViewModel coachDetailsViewModel = this.modelMapper.map(coach, CoachDetailsViewModel.class);
        coachDetailsViewModel.setDescription(details.getDescription());
        coachDetailsViewModel.setDuration(details.getDuration());
        coachDetailsViewModel.setGoals(details.getGoals());
        coachDetailsViewModel.setIntroductionVideo(details.getIntroductionVideo());
        coachDetailsViewModel.setResource(size);
        return coachDetailsViewModel;
    }


    @Override
    @Transactional
    public boolean deleteCoachById(String coachId) {
        boolean existsBOEntitiesByCoachesId = this.businessOwnerRepository.existsBO_EntitiesByCoachesId(coachId);
        boolean employeeCoachEntityByCoachesId = this.employeeCoachRepository.existsEmployeeCoachEntityByCoachesId(coachId);

        if (existsBOEntitiesByCoachesId || employeeCoachEntityByCoachesId) {
            System.out.println("Cannot delete now because of relations!!!!");
            return false;
        } else {
            this.sessionRepository.deleteByCoachId(coachId);
            this.coachDetailsRepository.deleteByCoachId(coachId);
            this.coachRepository.deleteById(coachId);
            return true;
        }
    }

    @Override
    public void addCoachByBO(String userBO_email, String coachId) {

        CoachEntity coach = this.coachRepository.findById(coachId).get();
        BO_Entity bo_entity = this.businessOwnerRepository.findByBOEmail(userBO_email);

        if (bo_entity == null) {
            BO_Entity newBO = new BO_Entity();
            newBO.setBOEmail(userBO_email)
                    .setCoaches(List.of(coach));
            this.businessOwnerRepository.save(newBO);
        } else {
            List<CoachEntity> coaches = bo_entity.getCoaches();
            coaches.add(coach);
            bo_entity.setCoaches(coaches);
            this.businessOwnerRepository.save(bo_entity);
        }
    }

    @Override
    public List<CoachViewModel> getAllCoachesByBO(String BO_email) {

        BO_Entity bo_entity = this.businessOwnerRepository.findByBOEmail(BO_email);
        if (bo_entity == null) {
            return null;
        }
        List<CoachEntity> coaches = bo_entity.getCoaches();

        return coaches.stream().map((m) ->
                this.modelMapper.map(m, CoachViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<CoachViewModel> getAllCoachesByAdminBO(String bOemail) {
        List<CoachEntity> coachEntities = this.coachRepository.findAll();
        BO_Entity bo_entity = this.businessOwnerRepository.findByBOEmail(bOemail);
        if (bo_entity == null) {
            return coachEntities.stream().map((m) ->
                    this.modelMapper.map(m, CoachViewModel.class)).collect(Collectors.toList());
        }

        List<CoachEntity> coachesBo = bo_entity.getCoaches();

        List<CoachEntity> filtered = coachEntities.stream()
                .filter(o -> !coachesBo.contains(o)).collect(Collectors.toList());

        return filtered.stream().map((m) ->
                this.modelMapper.map(m, CoachViewModel.class)).collect(Collectors.toList());
    }


    @Override
    public void deleteCoachByBO(String BO_email, String coachId) {
        BO_Entity bo_entity = this.businessOwnerRepository.findByBOEmail(BO_email);
        List<CoachEntity> coaches = bo_entity.getCoaches();
        coaches.remove(this.coachRepository.findById(coachId).get());
        bo_entity.setCoaches(coaches);
        this.businessOwnerRepository.save(bo_entity);
    }

    @Override
    public List<CoachSearchViewModel> findCoaches(SearchCriteria searchCriteria, String BO_email) {
        List<CategoryBindingModel> currentCategories = searchCriteria.getCategories();
        List<LanguageBindingModel> currentLanguages = searchCriteria.getLanguages();

        List<LanguageEntity> languages = new ArrayList<>();
        List<CategoryEntity> categories = new ArrayList<>();

        if (currentCategories != null) {
            categories = searchCriteria.getCategories()
                    .stream().map(c -> {
                        Category category = Category.valueOf(c.getCategory());
                        return this.categoryRepository.findByCategory(category);
                    }).collect(Collectors.toList());
        }
        if (currentLanguages != null) {
            languages = searchCriteria.getLanguages().stream()
                    .map(l -> {
                        Language language = Language.valueOf(l.getLanguage());
                        return this.languageRepository.findByLanguage(language);
                    }).collect(Collectors.toList());
        }

        List<CoachEntity> coachesEntities =
                this.coachRepository.findDistinctAllByCategoriesInOrLanguagesIn(categories, languages);

        BO_Entity bo_entity = this.businessOwnerRepository.findByBOEmail(BO_email);
        if (bo_entity == null) {
            return coachesEntities.stream()
                    .map(m -> this.modelMapper.map(m, CoachSearchViewModel.class))
                    .collect(Collectors.toList());
        }
        List<CoachEntity> coaches = bo_entity.getCoaches();

        List<CoachSearchViewModel> result = new ArrayList<>();
        coachesEntities.forEach(o -> {
            CoachSearchViewModel current = this.modelMapper.map(o, CoachSearchViewModel.class);
            current.setOwned(coaches.contains(o));
            result.add(current);
        });

        return result;
    }

    @Override
    public List<CoachSearchViewModel> findCoachesByEmployee(String BO_email, String employeeEmail) {
        BO_Entity bo_entity = this.businessOwnerRepository.findByBOEmail(BO_email);
        if (bo_entity == null) {
            return null;
        }
        List<CoachEntity> coaches = bo_entity.getCoaches();
        EmployeeCoachEntity employee = this.employeeCoachRepository.findByEmailEmployee(employeeEmail);
        if (employee == null) {
            return coaches.stream()
                    .map(m -> this.modelMapper.map(m, CoachSearchViewModel.class))
                    .collect(Collectors.toList());
        }
        List<CoachEntity> coachesEmployee = employee.getCoaches();

        List<CoachSearchViewModel> result = new ArrayList<>();
        coaches.forEach(o -> {
            CoachSearchViewModel current = this.modelMapper.map(o, CoachSearchViewModel.class);
            current.setOwned(coachesEmployee.contains(o));
            result.add(current);
        });

        return result;
    }


    @Override
    public <T> void sendMessageOutBoundEmployeeCompanyOwnerInfo(T kafkaModel) {
        MessageChannel messageChannel = this.coachStream.outboundEmployeeCompanyOwnerInfo();
        messageChannel.send(MessageBuilder
                .withPayload(kafkaModel)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }


    @Override
    public void feedbackToCoach(CoachFeedbackBindingModel feedbackInfo, String coachId, String email) {
        CoachEntity coach = this.coachRepository.findById(coachId).get();
        FeedbackEntity feedback = new FeedbackEntity();
        feedback.setSubjects(feedbackInfo.getSubjects())
                .setMessage(feedbackInfo.getMessage())
                .setCoach(coach)
                .setEmployeeEmail(email);
        this.feedBackRepository.save(feedback);

        sendMail(coach.getEmail(), email, feedbackInfo.getSubjects(), feedbackInfo.getMessage());

    }

    private void sendMail(String coachEmail, String userEmail, String subjectFeedback, String feedback) {

        String subject = "Feedback subject:" + subjectFeedback + "from" + userEmail + ".";

        String content = "Feedback content:" + feedback + ".";

        this.emailService.sendMessageWithAttachment(coachEmail, subject, content);
    }

    @Override
    public void bookCoachSessionByEmployee(String coachId, String id, String email) {

        EmployeeCoachEntity employee = this.employeeCoachRepository.findByEmailEmployee(email);
        CoachEntity coach = this.coachRepository.findById(coachId).get();
        SessionEntity session = this.sessionRepository.findById(id).get();
        session.setAvailable(false);
        this.sessionRepository.save(session);

    }

    @Override
    public void bookCoachByEmployee(String id, String email) {
        EmployeeCoachEntity employee = this.employeeCoachRepository.findByEmailEmployee(email);
        CoachEntity coach = this.coachRepository.findById(id).get();

        if (employee == null) {
            EmployeeCoachEntity newEmployee = new EmployeeCoachEntity();
            newEmployee.setEmailEmployee(email)
                    .setCoaches(List.of(coach));
            this.employeeCoachRepository.save(newEmployee);
        } else {
            List<CoachEntity> coaches = employee.getCoaches();
            coaches.add(coach);
            employee.setCoaches(coaches);
            this.employeeCoachRepository.save(employee);
        }
    }

    @Override
    public List<CoachSearchViewModel> getRandomCoaches(String boEmail,String employeeEmail) {

        BO_Entity businessOwner = this.businessOwnerRepository.findByBOEmail(boEmail);
        List<CoachEntity> coaches = businessOwner.getCoaches();
        List<CoachEntity> randomCoaches = new ArrayList<>();
        if(coaches.size()<=2){
            return coaches.stream()
                    .map(m -> this.modelMapper.map(m, CoachSearchViewModel.class))
                    .collect(Collectors.toList());
        }
        for (int i = 0; i < RANDOM_EMPLOYEE_COACHES_UPPERBOUND; i++) {
            int randomIndex = generateRandomNumberInBound(coaches.size());
            randomCoaches.add(coaches.get(randomIndex));
            coaches.remove(randomIndex);
        }
        EmployeeCoachEntity employee = this.employeeCoachRepository.findByEmailEmployee(employeeEmail);

        if (employee == null) {
            return randomCoaches.stream()
                    .map(m -> this.modelMapper.map(m, CoachSearchViewModel.class))
                    .collect(Collectors.toList());
        }

        List<CoachEntity> coachesEmployee = employee.getCoaches();

        List<CoachSearchViewModel> result = new ArrayList<>();
        coaches.forEach(o -> {
            CoachSearchViewModel current = this.modelMapper.map(o, CoachSearchViewModel.class);
            current.setOwned(coachesEmployee.contains(o));
            result.add(current);
        });


        return result.stream()
                .map(c -> this.modelMapper.map(c, CoachSearchViewModel.class))
                .collect(Collectors.toList());

    }

    private int generateRandomNumberInBound(int upperBound){
        Random rand = new Random();
        return rand.nextInt(upperBound);
    }

    @Override
    public CoachEditViewModel findCoachById(String coachId) {
        CoachEntity coach = this.coachRepository.findById(coachId).get();
        CoachEditViewModel map = this.modelMapper.map(coach, CoachEditViewModel.class);
        return map;
    }

    @Override
    public <Т> void sendMessageOutBoundCoachDateSeasonInfo(Т kafkaModel) {
        MessageChannel messageChannel = this.coachStream.outboundCoachInfo();
        messageChannel.send(MessageBuilder
                .withPayload(kafkaModel)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

    @Override
    @Transactional
    public void editCoachByAdmin(CoachEditBindingModel coachEditBindingModel, String coachId) throws IOException {
        CoachEntity coach = this.coachRepository.findById(coachId).get();

        this.sessionRepository.deleteByCoach(coach);
        this.employeeCoachRepository.deleteByCoachesContains(coach);
        coach.setSessions(null);

        coach.setName(coachEditBindingModel.getName())
                .setEmail(coachEditBindingModel.getEmail())
                .setCompany(coachEditBindingModel.getCompany())
                .setTopic(coachEditBindingModel.getTopic())
                .setPrice(coachEditBindingModel.getPrice())
                .setStartDate(coachEditBindingModel.getStartDate())
                .setEndDate(coachEditBindingModel.getEndDate());

        pictureManage(coachEditBindingModel, coach);

        List<SessionEntity> newSession = this.createSession(coach, coachEditBindingModel.getStartDate(), coachEditBindingModel.getEndDate());
        coach.setSessions(newSession);

        this.coachRepository.save(coach);
    }

    private MultipartFile convertFileToMultipartFile(File file) {

        Path path = Paths.get(file.getPath());
        String name = file.getName();
        String originalFileName = file.getName();
        String contentType = "application/octet-stream";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return new MockMultipartFile(name,
                originalFileName, contentType, content);
    }


    private void pictureManage(CoachEditBindingModel coachEditBindingModel, CoachEntity coach) throws IOException {
        picControl(coachEditBindingModel, coach);
    }

    private void picControl(CoachEditBindingModel coachEditBindingModel, CoachEntity coach) throws IOException {
        if (coachEditBindingModel.getPicture() != null) {
            if (coach.getPicture() != null) {
                deletePicture(coachEditBindingModel, coach);
            } else {
                uploadPicture(coachEditBindingModel, coach);
            }
        }
    }

    private void deletePicture(CoachEditBindingModel coachEditBindingModel, CoachEntity coach) throws IOException {
        this.cloudinaryService.deleteImage(idPic(coach), Collections.emptyMap());
        uploadPicture(coachEditBindingModel, coach);
    }

    private String idPic(CoachEntity coach) {
        return coach.getPicture()
                .substring(coach.getPicture().lastIndexOf(CLOUDINARY_CUT_START) + CLOUDINARY_CUT_INDEX,
                        coach.getPicture().lastIndexOf(CLOUDINARY_CUT_END));
    }

    private void uploadPicture(CoachEditBindingModel userProfile, CoachEntity userEntity) throws IOException {
//        MultipartFile image = convertFileToMultipartFile(userProfile.getPicture());
        MultipartFile image = userProfile.getPicture();
        String imageUrl = cloudinaryService.uploadImage(image);
        userEntity.setPicture(imageUrl);
    }

}

