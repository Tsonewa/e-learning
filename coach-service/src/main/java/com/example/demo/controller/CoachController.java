package com.example.demo.controller;

import com.example.demo.exception.IncorrectData;
import com.example.demo.model.binding.*;
import com.example.demo.model.binding.SeachCriteriaModels.SearchCriteria;
import com.example.demo.model.view.*;
import com.example.demo.service.CoachService;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static com.example.demo.constants.Constants.*;

@RestController
@RequestMapping("/coaches")
public class CoachController {

    private final CoachService coachService;


    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping("/all-admin")
    public ResponseEntity<?> getAllCoachesByAdmin() {

        List<CoachViewModelByAdmin> allByAdmin = this.coachService.getAllByAdmin();

        return ResponseEntity.status(200).body(allByAdmin);
    }


    @PostMapping(path = "/create")
    public ResponseEntity<?> createCoachByAdmin(
            @RequestBody CoachBindingAdminModel coachBindingModel,
            BindingResult bindingResult) throws IOException, IncorrectData {

        if (bindingResult.hasErrors()) {
            throw new IncorrectData("Provide valid data");
        }

        CoachViewModelId coachByAdmin = this.coachService.createCoachByAdmin(coachBindingModel);

//        CoachDateTimeView coachDateTimeView = new CoachDateTimeView();
//        coachDateTimeView
//                .setCoachId(coachByAdmin.getId())
//                .setEndDate(coachBindingModel.getEndDate())
//                .setStartDate(coachBindingModel.getStartDate());
//        this.coachService.sendMessageOutBoundCoachDateSeasonInfo(coachDateTimeView);

        return ResponseEntity.status(201).body(coachByAdmin);
    }

    @PutMapping("/create/picture")
    public ResponseEntity<?> addCoachByAdminPicture(@RequestPart(name = "picture") MultipartFile picture,
                                                    @RequestPart(name = "id") String id) throws IOException {
        this.coachService.addPictureToCoach(id, picture);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editCoachByAdmin(@Valid @ModelAttribute CoachEditBindingModel coachEditBindingModel,
                                              BindingResult bindingResult, @RequestParam String id)
            throws IOException, IncorrectData {

        if (bindingResult.hasErrors()) {
            throw new IncorrectData("Provide valid data");
        }

        this.coachService.editCoachByAdmin(coachEditBindingModel, id);

        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/delete-by-admin")
    public ResponseEntity<?> deleteCoachByAdmin(@RequestParam String id) {

        boolean isDeleted = this.coachService.deleteCoachById(id);

        if (isDeleted) {
        return ResponseEntity.status(202).body(true);
        }
        return ResponseEntity.status(201).body(false);
    }

    @GetMapping("/bo-profile")
    public ResponseEntity<?> getAllCoachByBO(HttpServletRequest http) {

        String BOemail = http.getHeader(USER_MAIL);
        List<CoachViewModel> coachesByBO = this.coachService.getAllCoachesByBO(BOemail);
        return ResponseEntity.status(200).body(coachesByBO);
    }

    @GetMapping("/all-bo-admin")
    public ResponseEntity<?> getAllCoachByAdminBO(HttpServletRequest http) {

        String BOemail = http.getHeader(USER_MAIL);
        List<CoachViewModel> coachesByAminBO = this.coachService.getAllCoachesByAdminBO(BOemail);
        return ResponseEntity.status(200).body(coachesByAminBO);
    }

    @GetMapping("/coach")
    public ResponseEntity<?> getCoachByAdmin(@RequestParam String id) {

        CoachEditViewModel coachById = this.coachService.findCoachById(id);

        return ResponseEntity.status(200).body(coachById);
    }

    @GetMapping("/bo-add")
    public ResponseEntity<?> addCoachByBO(@RequestParam String id, HttpServletRequest http) {
        String userBO_Email = http.getHeader(USER_MAIL);

        this.coachService.addCoachByBO(userBO_Email, id);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/bo-delete")
    public ResponseEntity<?> deleteCoachById(@RequestParam String id, HttpServletRequest http) {
        String BOemail = http.getHeader(USER_MAIL);

        this.coachService.deleteCoachByBO(BOemail, id);

        return ResponseEntity.noContent().build();
    }


    @PostMapping("/search")
    public ResponseEntity<?> searchCoaches(@RequestBody SearchCriteria searchCriteria, HttpServletRequest http) {
        String BOemail = http.getHeader(USER_MAIL);

        List<CoachSearchViewModel> coaches = this.coachService.findCoaches(searchCriteria, BOemail);

        return ResponseEntity.ok(coaches);

    }

//    @GetMapping("/employee-company")
//    public ResponseEntity<?> getEmployeeCompany(HttpServletRequest http) {
//        String employeeEmail = http.getHeader(USER_MAIL);
//
//        this.coachService.sendMessageOutBoundEmployeeCompanyOwnerInfo(employeeEmail);
//
//        return ResponseEntity.status(202).build();
//    }

//    @StreamListener(COMPANY_OWNER_INITIALIZED)
//    public void getCompanyOwnerEmail(@Payload CompanyOwnerEmail emailCompany) {
//
//        List<CoachViewModel> allCoachesByBO = this.coachService.getAllCoachesByBO(emailCompany.getEmailCompanyOwner());
//
//        this.getAllCoachesByEmployee(allCoachesByBO, emailCompany.getEmailEmployee());
//
//    }

//    @GetMapping("/employee-coaches")
//    public ResponseEntity<?> getAllCoachesByEmployee(List<CoachViewModel> allCoachesByBO, String emailEmployee) {
//
//        List<CoachViewModel> coachesByEmployee = this.coachService.findCoachesByEmployee(emailEmployee);
//
//        return ResponseEntity.ok(coachesByEmployee);
//    }

    @GetMapping("/coaches-employee")
    @ResponseBody
    public ResponseEntity<?> getAllCoachesByEmployee(@RequestParam String emailCompanyOwner,
                                                     HttpServletRequest http) {

        String email = http.getHeader(USER_MAIL);

        List<CoachSearchViewModel> allCoachesByBO = this.coachService.findCoachesByEmployee(emailCompanyOwner, email);

        return ResponseEntity.ok(allCoachesByBO);
    }

    @GetMapping("/coach-session")
    public ResponseEntity<?> getAllCoachSession(@RequestParam String id){

        List<SessionViewModel> allCoachSession = this.coachService.getAllCoachSession(id);

        return ResponseEntity.ok(allCoachSession);
    }

    @PostMapping("/employee-book-session")
    public ResponseEntity<?> employeeBookCoachSession(@RequestBody CoachSessionBookBinding coachSessionBookBinding, HttpServletRequest http) {

        String email = http.getHeader(USER_MAIL);
        //Aya
        String idCoach = coachSessionBookBinding.getIdCoach();
        String id = coachSessionBookBinding.getId();

        this.coachService.bookCoachSessionByEmployee(idCoach, id, email);

        return ResponseEntity.status(200).build();
    }



    @GetMapping("/details-by-employee")
    public ResponseEntity<?> detailsCoachModal(@RequestParam String id) {

        CoachDetailsViewModel coachViewModel = this.coachService.findCoachDetails(id);

        return ResponseEntity.ok(coachViewModel);
    }

    @PostMapping("/employee-book")
    public ResponseEntity<?> employeeBookCoach(@RequestBody CoachIdBindingModel model, HttpServletRequest http) {

        String email = http.getHeader(USER_MAIL);
        //Aya

        this.coachService.bookCoachByEmployee(model.getId(), email);

        return ResponseEntity.status(200).build();
    }

    @PostMapping("/feedback")
    public ResponseEntity<?> feedback(@Valid @RequestBody CoachFeedbackBindingModel coachBindingModel,
                                      BindingResult bindingResult, @RequestParam String id,
                                      HttpServletRequest http) throws IncorrectData {

        if (bindingResult.hasErrors()) {
            throw new IncorrectData("Provide valid data");
        }

        String email = http.getHeader(USER_MAIL);

        this.coachService.feedbackToCoach(coachBindingModel, id, email);

        return ResponseEntity.noContent().build();
    }

    @ResponseBody
    @GetMapping("/employee/dashboard")
    public List<CoachSearchViewModel> getRandomCourses(@RequestParam String boEmail,HttpServletRequest http){
        String email = http.getHeader(USER_MAIL);
        List<CoachSearchViewModel> randomCoaches = this.coachService.getRandomCoaches(boEmail,email);


        return randomCoaches;
    }
}
