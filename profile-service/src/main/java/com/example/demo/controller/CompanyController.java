package com.example.demo.controller;

import com.example.demo.model.binding.CompanyNameBindingModel;
import com.example.demo.model.binding.UserRegisterCheckCompanyDto;
import com.example.demo.model.view.CompanyView;
import com.example.demo.service.CompanyService;
import org.apache.tomcat.jni.Local;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.demo.constants.Constants.COMPANY_NAME_INFO;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public ResponseEntity<?> getAllCompanies(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "limit", defaultValue = "4") int limit) {

        List<CompanyView> allCompanies = this.companyService.getAllCompanies(page, limit);

        return ResponseEntity.ok().body(allCompanies);
    }

    @StreamListener(COMPANY_NAME_INFO)
    public void checkCompanyName(@Payload UserRegisterCheckCompanyDto userTransfer){

         boolean checkForCompanyName =  this.companyService.checkCompanyName(userTransfer.getCompanyName());
         userTransfer.setCompanyExist(checkForCompanyName);
        this.companyService.sendMessageOutBoundCompanyNameCheck(userTransfer);
    }

    @PostMapping("/name")
    public ResponseEntity<?> checkName(@RequestBody CompanyNameBindingModel companyNameBindingName){
        boolean isExist = this.companyService.checkCompanyName(companyNameBindingName.getCompanyName());
        return ResponseEntity.ok(isExist);
    }
}
