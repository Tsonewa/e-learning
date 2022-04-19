package com.example.demo.service;

import com.example.demo.model.binding.CoachBindingAdminModel;
import com.example.demo.model.binding.CoachEditBindingModel;
import com.example.demo.model.binding.CoachFeedbackBindingModel;
import com.example.demo.model.binding.CompanyOwnerEmail;
import com.example.demo.model.binding.SeachCriteriaModels.SearchCriteria;
import com.example.demo.model.view.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface CoachService {

    List<CoachViewModelByAdmin> getAllByAdmin();

    CoachViewModelId createCoachByAdmin(CoachBindingAdminModel coachBindingModel) throws IOException;

    boolean deleteCoachById(String coachId);

    void addCoachByBO(String userBO_email, String coachId);

    List<CoachViewModel> getAllCoachesByBO(String bOemail);

    void deleteCoachByBO(String BOemail, String coachId);

    List<CoachSearchViewModel> findCoaches(SearchCriteria searchCriteria, String bo_email);

    List<CoachSearchViewModel> findCoachesByEmployee(String BO_email, String employeeEmail);

    void feedbackToCoach(CoachFeedbackBindingModel coachFeedbackBindingModel, String id, String email);

    void bookCoachSessionByEmployee(String coachId, String id, String email);

    CoachEditViewModel findCoachById(String coachId);

    <T> void sendMessageOutBoundCoachDateSeasonInfo(T kafkaModel);

    void editCoachByAdmin(CoachEditBindingModel coachEditBindingModel, String coachId) throws IOException;

    <T>  void sendMessageOutBoundEmployeeCompanyOwnerInfo(T kafkaModel);

    List<CoachViewModel> getAllCoachesByAdminBO(String bOemail);

    void addPictureToCoach(String id, MultipartFile picture) throws IOException;

    List<SessionViewModel> getAllCoachSession(String id);

    CoachDetailsViewModel findCoachDetails(String id);

    void bookCoachByEmployee(String id, String email);

    List<CoachSearchViewModel> getRandomCoaches(String boEmail,String employeeEmail);

}
